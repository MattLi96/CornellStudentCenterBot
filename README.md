# Cornell Student Center Bot
A bot that automatically adds classes for pre-enroll using selenium. 
No longer useful due to Cornell's recent upgrade to student center.

I have plans to change this into a bot that refreshes on student center and gets a class once it is available. 

#Usage
Using the bot is relatively simple. Bot opperation is taken care of from the core package.

First run the encrypter and input your password. This stores your password in an encrypted file called info.txt. After you're done
simply delete the file and/or change the key located on the top of the encrypter.

Afterwards, change the MainNoGui main method to add the classes you would like and run.

#Dependency
This bot uses selenium 2.49.0. You will need to download selenium and add it to the build path.