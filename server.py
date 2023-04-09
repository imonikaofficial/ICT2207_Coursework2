import socket
import mysql.connector
import subprocess
import random
import secrets
import os
from cryptography.hazmat.primitives.serialization import load_der_public_key
from cryptography.hazmat.primitives.asymmetric import rsa, padding
from cryptography.hazmat.primitives import serialization, hashes

server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
host = socket.gethostname()
port = 18537
server_socket.bind((host, port))
server_socket.listen()

database = mysql.connector.connect(host="localhost",user="adminMoni",password="I#0LX9CS0SeF",database="ict2207")
statement_executor = database.cursor()

add_user_statement = ("INSERT INTO Users "
"VALUES (%s, %s, %s, %s)")

select_user_statement = "SELECT * FROM Users WHERE UserName = %s"




bool_to_byte = {True: b"\x01", False: b"\x00"}

def verify_user_exists(parameter_list):
    username = parameter_list[0]
    user_exists = True
    #check user exists
    print("finding user:", username)
    statement_executor.execute(select_user_statement, (username,))
    statement_executor.fetchall()
    print(statement_executor.rowcount)
    if statement_executor.rowcount == 0:
        user_exists = False
        print("user not found")
    return bool_to_byte[user_exists]

print(verify_user_exists(["testname2"]))

def verify_hash(username, hash):
    hash_match = False
    statement_executor.execute(select_user_statement, (username,))
    for r in statement_executor:
        print(r[2], hash)
        if r[2] == hash:
            hash_match = True
        break
    return bool_to_byte[hash_match]

def verify_user_password(parameter_list):
    hash_match = verify_hash(parameter_list[0], parameter_list[1])
    return hash_match

def add_user(parameter_list):
    #add user
    user_availiable = True
    username = parameter_list[0]
    password_hash = parameter_list[1]
    statement_executor.execute(select_user_statement, (username,))
    statement_executor.fetchall()
    if statement_executor.rowcount > 0:
        user_availiable = False
    uid = 0
    check = False
    statement_executor.execute(select_user_statement, (username,))
    while not check:
        uid = random.randint(1000000,9999999)
        check = True
        for r in statement_executor:
            if r[0] == uid:
                check = False
                break
    if user_availiable:
        statement_executor.execute(add_user_statement, (uid, username, password_hash, os.urandom(32)))
        database.commit()
        print("added user:", username)
    return bool_to_byte[user_availiable]

print(add_user(["testname2",b"meme"]))

def get_encrypted_key(parameter_list):
    key = b""
    username = parameter_list[0]
    password_hash = parameter_list[1]
    rsakeybytes = b"\x00".join(parameter_list[2:])

    rsakey = load_der_public_key(rsakeybytes)

    statement_executor.execute(select_user_statement, (username,))
    for r in statement_executor:
        print([i for i in r[3]])
        if r[2] == password_hash and r[1] == username.decode():
            key = rsakey.encrypt(bytes(r[3]), padding.OAEP(mgf=padding.MGF1(algorithm=hashes.SHA256()),algorithm=hashes.SHA256(), label=None))
        break

    return key



def process_data(parameters):
    command = parameters[0]
    parameter_list = parameters[1:].split(b"\x00")
    return_data = b""
    if command == 1:
        return_data = verify_user_exists(parameter_list)
    if command == 2:
        return_data = verify_user_password(parameter_list)
    if command == 3:
        return_data = add_user(parameter_list)
    if command == 4:
        return_data = get_encrypted_key(parameter_list)
    return_data_length_bytes = (len(return_data) + 1).to_bytes(4,"big")
    return_data_full = return_data_length_bytes + command.to_bytes(1,"big") + return_data
    print(return_data_full)
    return return_data_full


while True:
    client_socket, client_address = server_socket.accept()
    print(client_address)
    data_length_bytes = client_socket.recv(4)
    data_length = int.from_bytes(data_length_bytes, "big")
    data = client_socket.recv(data_length)
    return_data = process_data(data)
    client_socket.send(return_data)
    client_socket.close()