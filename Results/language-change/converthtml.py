import re
import BeautifulSoup
import os 
import html2text
import urllib
import sys 

reload(sys)
sys.setdefaultencoding('utf-8')
user_input = raw_input("Enter the path of your file: ")
directory = os.listdir(user_input)
dirpath = os.path.abspath(user_input)

nmvcount = 0
amvcount = 0
failed=0

print(dirpath)
for file in directory:
    filename, file_extension = os.path.splitext(file)
    if file_extension == ".html":
        htmlfile = dirpath + "/" + file
        html=filename.split(".")[-1]
        html_file = open(htmlfile, 'r')
        source_code = html_file.read() 
        text = html2text.html2text(source_code)
        if text.find('NoMatchingViewException') !=-1 :
            print("NoMatchingViewException: " + html)
            nmvcount=nmvcount+1
            failed=failed+1
        if text.find("AmbiguousViewMatcherException") !=-1 :
            print("AmbiguousViewMatcherException: " + html)
            amvcount=amvcount+1
            failed=failed+1
        
print("Total NoMatchingViewException: " + str(nmvcount))
print("Total AmbiguousViewMatcherException: " + str(amvcount))
print("Total Failed Tests: " + str(failed))
