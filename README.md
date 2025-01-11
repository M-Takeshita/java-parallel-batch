
# java-parallel-batch

大量データ処理や非同期タスクの実行を効率化することを目的とし、検証・記録する。


## 特徴

- **並列処理対応**: Javaの並列処理機能（ExecutorService）を活用。
- **スケーラビリティ**: スレッド数やリソースを柔軟に調整可能。（クラウドネイティブを考慮し環境変数化することが個人的に望ましい）
- **例外ハンドリング**: 安全なエラーハンドリングで処理の信頼性を向上。
- **ログ管理**: 処理状況をロギングする仕組みを実装。

## 使用技術

- **Java**: 11以上  
- **依存ライブラリ**: 
  - Lombok (コード簡素化のため)
  - SLF4J (ロギング用)

## セットアップ

### 必要条件
- Java 11以上がインストールされていること
- Maven または Gradle のインストール

<!-- ### クローンとビルド

```bash
git clone https://github.com/your-username/parallel-batch.git
cd parallel-batch
mvn clean install
```

### 実行方法

以下のコマンドでメインクラスを実行できます:

```bash
java -jar target/parallel-batch-1.0.0.jar
```

または、IDE（例: IntelliJ IDEA、VS Code）で直接 `Main` クラスを実行してください。

## 使用例

以下の例は、指定されたデータリストを複数のスレッドで処理するサンプルです。

```java
ExecutorService executorService = Executors.newFixedThreadPool(4);

List<Callable<String>> tasks = Arrays.asList(
    () -> "Task 1 result",
    () -> "Task 2 result",
    () -> "Task 3 result"
);

List<Future<String>> results = executorService.invokeAll(tasks);

for (Future<String> result : results) {
    System.out.println(result.get());
}

executorService.shutdown();
```

## プロジェクト構成

```
src/
├── main/
│   ├── java/
│   │   ├── com.example.batch/
│   │   │   ├── Main.java
│   │   │   ├── BatchProcessor.java
│   │   │   └── TaskWorker.java
│   └── resources/
│       └── application.properties
├── test/
│   ├── java/
│   │   └── com.example.batch/
│   │       └── BatchProcessorTest.java
``` -->
