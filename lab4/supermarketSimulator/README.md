Многопоточный supermarket simulator, реализованный с использованием идиомы Producer - Consumer.
В потоке Producer выполняются действия пользователя.
В потоке Consumer выполняется обработка (оплата) покупок пользователей.

Использование:
Запустить clean-run.bat предправительно установив менеджер пакетов maven. [Download page](https://maven.apache.org/download.cgi) .
 
[Installation guide](https://maven.apache.org/guides/getting-started/windows-prerequisites.html)

Запуск
mvn clean package && java -jar target/task1-1.0.SNAPSHOT.jar <input file> <output file>
