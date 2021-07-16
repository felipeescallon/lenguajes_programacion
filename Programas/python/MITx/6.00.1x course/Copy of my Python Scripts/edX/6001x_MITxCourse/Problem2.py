# -*- coding: utf-8 -*-
"""
Created on Tue Jul  7 22:47:37 2020

@author: Andrés Felipe Escallón Portilla
"""
#Problem 2
#0.0/10.0 puntos (calificado)
#Assume s is a string of lower case characters.
#Write a program that prints the number of times the string 'bob' occurs in s. For example, if s = 'azcbobobegghakl', then your program should print
#Number of times bob occurs is: 2
# Paste your code into this box 
#Given a string s
s = 'azcbobobegghakl'
count=0
length=len(s)
num_times=0
while count<length-2:
    if(s[count]=='b' and s[count+1]=='o' and s[count+2]=='b'):
        num_times+=1
        count+=1      
    else:
        num_times=num_times
        count+=1
print("Number of times 'bob' occurs in the given string s="+"'"+ s +"'"+" is: " + str(num_times))
