executing command `java -jar lab13.jar`, the result:

```bash
Type here amount of notes
2
Please, type here address number: 1
st. Kharkiv
Please, type here phone number: 1
+380689619347
Please, type here address number: 2
st. Kharkiv
Please, type here phone number: 2
+380991793046
Seeding passed elements to your user storage...
Elements: {[[+380689619347], [st. Kharkiv]][[+380991793046], [st. Kharkiv]]}
Length: 2
Start multhithreading part
Please, type here number of threads
10
Please type here timeout for threads in seconds
1
15 => pool-1-thread-1 started
Finding all phone numbers in Kharkiv with Lifecell and Vodafone in thread number: 15
[[[+380991793046], [st. Kharkiv]]] => 15
[] => 15
15 => pool-1-thread-1 ended:  time: 13
24 => pool-1-thread-10 started
Finding all phone numbers in Kharkiv with Lifecell and Vodafone in thread number: 24
[[[+380991793046], [st. Kharkiv]]] => 24
[] => 24
24 => pool-1-thread-10 ended:  time: 11
23 => pool-1-thread-9 started
Finding all phone numbers in Kharkiv with Lifecell and Vodafone in thread number: 23
[[[+380991793046], [st. Kharkiv]]] => 23
[] => 23
23 => pool-1-thread-9 ended:  time: 11
22 => pool-1-thread-8 started
Finding all phone numbers in Kharkiv with Lifecell and Vodafone in thread number: 22
[[[+380991793046], [st. Kharkiv]]] => 22
[] => 22
22 => pool-1-thread-8 ended:  time: 12
21 => pool-1-thread-7 started
Finding all phone numbers in Kharkiv with Lifecell and Vodafone in thread number: 21
[[[+380991793046], [st. Kharkiv]]] => 21
[] => 21
21 => pool-1-thread-7 ended:  time: 12
20 => pool-1-thread-6 started
Finding all phone numbers in Kharkiv with Lifecell and Vodafone in thread number: 20
[[[+380991793046], [st. Kharkiv]]] => 20
[] => 20
20 => pool-1-thread-6 ended:  time: 11
19 => pool-1-thread-5 started
Finding all phone numbers in Kharkiv with Lifecell and Vodafone in thread number: 19
[[[+380991793046], [st. Kharkiv]]] => 19
[] => 19
19 => pool-1-thread-5 ended:  time: 11
18 => pool-1-thread-4 started
Finding all phone numbers in Kharkiv with Lifecell and Vodafone in thread number: 18
[[[+380991793046], [st. Kharkiv]]] => 18
[] => 18
18 => pool-1-thread-4 ended:  time: 14
17 => pool-1-thread-3 started
Finding all phone numbers in Kharkiv with Lifecell and Vodafone in thread number: 17
[[[+380991793046], [st. Kharkiv]]] => 17
[] => 17
17 => pool-1-thread-3 ended:  time: 16
16 => pool-1-thread-2 started
Finding all phone numbers in Kharkiv with Lifecell and Vodafone in thread number: 16
[[[+380991793046], [st. Kharkiv]]] => 16
[] => 16
16 => pool-1-thread-2 ended:  time: 15

Process finished with exit code 0
```