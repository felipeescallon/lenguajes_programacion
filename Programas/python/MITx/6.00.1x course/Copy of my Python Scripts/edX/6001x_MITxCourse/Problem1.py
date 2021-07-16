# -*- coding: utf-8 -*-
"""
Created on Tue Jul  7 22:18:09 2020

@author: Andrés Felipe Escallón Portilla
"""
#Problem 1
#0.0/10.0 puntos (calificado)
#Assume s is a string of lower case characters.
#Write a program that counts up the number of vowels contained in the string s. Valid vowels are: 'a', 'e', 'i', 'o', and 'u'. For example, if s = 'azcbobobegghakl', your program should print:
#Number of vowels: 5
# Paste your code into this place
#Given a string s
s = 'azcbobobegghakl'
count=0
length=len(s)
num_vowels=0
while count<length:
    if(s[count]=='a' or s[count]=='e' or s[count]=='i' or s[count]=='o' or s[count]=='u'):
        #print(s[cont])
        num_vowels+=1
        count+=1      
    else:
        #print('else')
        num_vowels=num_vowels
        count+=1
print("Number of vowels in the given string s="+"'"+s+"' is:",num_vowels)
#print(s[:])