import re
import BeautifulSoup
import os 
import html2text
import urllib
import sys 

reload(sys)
#dirpath = os.path.abspath(user_input)
#print(dirpath)
user_input = raw_input("Enter the path of your files: ")
directory = os.listdir(user_input)
dirpath = os.path.abspath(user_input)
for file in directory:
    filename, file_extension = os.path.splitext(file)
    if file_extension == ".html":
        htmlfile = dirpath + "/" + file
        html=filename.split(".")[-1]
        html_file = open(htmlfile, 'r')
        source_code = html_file.read()
        text = html2text.html2text(source_code)
        if text.find('****MATCHES****') !=-1 :
            print(file)
        for line in text.split("\n"):
            if line.find('****MATCHES****') !=-1 :
                res = line.partition("->")[2].partition("{")[0]
                print("MATCH: " + res)

