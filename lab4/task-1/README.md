RecursiveWalk
Рекурсивный подчсет хэш-сумм файлов в директориях, указанных в входном файле, с использованием алгоритма [Fowler–Noll–Vo hash function](https://en.wikipedia.org/wiki/Fowler%E2%80%93Noll%E2%80%93Vo_hash_function)

Использование:
Запустить clean-run.bat предправительно установив менеджер пакетов maven, java 8 jdk. [Download page](https://maven.apache.org/download.cgi) .
 
[Installation guide on windows](https://maven.apache.org/guides/getting-started/windows-prerequisites.html)

Запуск
```bat
mvn clean package && java -jar target/task1-1.0.SNAPSHOT.jar <input file> <output file>
```
