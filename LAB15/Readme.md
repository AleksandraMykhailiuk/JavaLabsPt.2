Result of program compilation:
```bash
Type here amount of notes
2
Please, type here address number: 1
st. Jasmine, Kharkiv
Please, type here phone number: 1
+380689619347
Please, type here address number: 2
st. Jasmine, Kharkiv
Please, type here phone number: 2
+380991793046
Seeding passed elements to your user storage...
Elements: [[[+380689619347], [st. Jasmine, Kharkiv]], [[+380991793046], [st. Jasmine, Kharkiv]]]
Length: 2
Start multhithreading part
Please, type here number of threads
4
Please type here timeout for threads in seconds
4
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
35 => pool-1-thread-2 started
36 => pool-1-thread-3 started
Finding all phone numbers in Kharkiv with Lifecell and Vodafone in thread number: 36
37 => pool-1-thread-4 started
Finding all phone numbers in Kharkiv with Lifecell and Vodafone in thread number: 37
34 => pool-1-thread-1 started
Finding all phone numbers in Kharkiv with Lifecell and Vodafone in thread number: 35
Finding all phone numbers in Kharkiv with Lifecell and Vodafone in thread number: 34
[[[+380991793046], [st. Jasmine, Kharkiv]]] => 36
[[[+380991793046], [st. Jasmine, Kharkiv]]] => 34
[[[+380991793046], [st. Jasmine, Kharkiv]]] => 37
[[[+380991793046], [st. Jasmine, Kharkiv]]] => 35
[] => 34
[] => 37
[] => 36
[] => 35
37 => pool-1-thread-4 ended:  time: 13
34 => pool-1-thread-1 ended:  time: 13
37 => pool-1-thread-4 started
Finding all phone numbers in Kharkiv with Lifecell and Vodafone in thread number: 37
[[[+380991793046], [st. Jasmine, Kharkiv]]] => 37
[] => 37
36 => pool-1-thread-3 ended:  time: 19
35 => pool-1-thread-2 ended:  time: 19
35 => pool-1-thread-2 started
36 => pool-1-thread-3 started
Finding all phone numbers in Kharkiv with Lifecell and Vodafone in thread number: 36
Finding all phone numbers in Kharkiv with Lifecell and Vodafone in thread number: 35
[[[+380991793046], [st. Jasmine, Kharkiv]]] => 36
[[[+380991793046], [st. Jasmine, Kharkiv]]] => 35
[] => 36
[] => 35
37 => pool-1-thread-4 ended:  time: 22
36 => pool-1-thread-3 ended:  time: 16
35 => pool-1-thread-2 ended:  time: 16
36 => pool-1-thread-3 started
Finding all phone numbers in Kharkiv with Lifecell and Vodafone in thread number: 36
35 => pool-1-thread-2 started
Finding all phone numbers in Kharkiv with Lifecell and Vodafone in thread number: 35
[[[+380991793046], [st. Jasmine, Kharkiv]]] => 36
[[[+380991793046], [st. Jasmine, Kharkiv]]] => 35
[] => 36
[] => 35
36 => pool-1-thread-3 ended:  time: 15
35 => pool-1-thread-2 ended:  time: 15
35 => pool-1-thread-2 started
Finding all phone numbers in Kharkiv with Lifecell and Vodafone in thread number: 35
[[[+380991793046], [st. Jasmine, Kharkiv]]] => 35
[] => 35
35 => pool-1-thread-2 ended:  time: 15
38 => pool-2-thread-1 started
Finding all phone numbers in Kharkiv with Lifecell and Vodafone in thread number: 38
[[[+380991793046], [st. Jasmine, Kharkiv]]] => 38
[] => 38
38 => pool-2-thread-1 ended:  time: 12
39 => pool-2-thread-2 started
Finding all phone numbers in Kharkiv with Lifecell and Vodafone in thread number: 39
[[[+380991793046], [st. Jasmine, Kharkiv]]] => 39
[] => 39
39 => pool-2-thread-2 ended:  time: 13
39 => pool-2-thread-2 started
Finding all phone numbers in Kharkiv with Lifecell and Vodafone in thread number: 39
[[[+380991793046], [st. Jasmine, Kharkiv]]] => 39
[] => 39
39 => pool-2-thread-2 ended:  time: 11
40 => pool-2-thread-3 started
Finding all phone numbers in Kharkiv with Lifecell and Vodafone in thread number: 40
[[[+380991793046], [st. Jasmine, Kharkiv]]] => 40
[] => 40
40 => pool-2-thread-3 ended:  time: 11
40 => pool-2-thread-3 started
Finding all phone numbers in Kharkiv with Lifecell and Vodafone in thread number: 40
[[[+380991793046], [st. Jasmine, Kharkiv]]] => 40
[] => 40
40 => pool-2-thread-3 ended:  time: 14
41 => pool-2-thread-4 started
Finding all phone numbers in Kharkiv with Lifecell and Vodafone in thread number: 41
[[[+380991793046], [st. Jasmine, Kharkiv]]] => 41
[] => 41
41 => pool-2-thread-4 ended:  time: 16
41 => pool-2-thread-4 started
Finding all phone numbers in Kharkiv with Lifecell and Vodafone in thread number: 41
[[[+380991793046], [st. Jasmine, Kharkiv]]] => 41
[] => 41
41 => pool-2-thread-4 ended:  time: 15
           Results           
       Type        |  Time  |
-----------------------------
 Not synchronized  |    65  |
     Synchronized  |    52  |
           Result  |   -13  |
Start serialization part part
Storing address book in file...
Storing completed successfully...
Deserializing the object...
Deserializing object completed...
[[[+380689619347], [st. Jasmine, Kharkiv]], [[+380991793046], [st. Jasmine, Kharkiv]]]
[]

Process finished with exit code 0
```