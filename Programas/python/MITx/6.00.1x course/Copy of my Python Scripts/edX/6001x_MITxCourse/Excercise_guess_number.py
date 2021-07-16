# -*- coding: utf-8 -*-
"""
Created on Wed Aug  5 21:00:58 2020

@author: Andrés Felipe Escallón Portilla
"""


#Exercise: guess my number
#Finger Exercises fecha límite Aug 5, 2020 18:30 -05
#In this problem, you'll create a program that guesses a secret number!
#The program works as follows: you (the user) thinks of an integer between 0 (inclusive) and 100 (not inclusive). The computer makes guesses, and you give it input - is its guess too high or too low? Using bisection search, the computer will guess the user's secret number!
#Here is a transcript of an example session:
#Please think of a number between 0 and 100!
#Is your secret number 50?
#Enter 'h' to indicate the guess is too high. Enter 'l' to indicate the guess is too low. Enter 'c' to indicate I guessed correctly.
#.
#.
#.
#Game over. Your secret number was: 83
x=100
num_guesses=0
low=0
high=x
ans=(low + high)/2.0
ans=int(ans)
confirm='h'

print('Please think of an integer number between 0 and 100')

while ans<x:
    print('Is your secret number: '+str(ans)+'?')
    confirm=input('Enter "h" to indicate the guess is too high. Enter "l" to indicate the guess is too low. Enter "c" to indicate I guessed correctly: ')
    print(confirm)

    while confirm[0]!='h'and confirm[0]!='l'and confirm[0]!='c':
      print('Sorry, I did not understand your input.')
      print('Is your secret number: '+str(ans)+'?')
      confirm=input('Enter "h" to indicate the guess is too high. Enter "l" to indicate the guess is too low. Enter "c" to indicate I guessed correctly: ')

    if confirm[0]=='h':        
     high=ans
     ans=(low + high)/2.0
     ans=int(ans)
    if confirm[0]=='l':        
     low=ans
     ans=(low + high)/2.0
     ans=int(ans)

    if confirm[0]=='c':        
     print('Game over. Your secret number was: '+str(ans))
     break
     
    num_guesses+=1
    print('num_guesses: '+str(num_guesses))
  
  