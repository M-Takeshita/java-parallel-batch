
# java-parallel-batch

大量データ処理や非同期タスクの実行を効率化することを目的とし、検証・記録する。


## 特徴

- **並列処理対応**: Javaの並列処理機能（ExecutorService）を活用。
- **スケーラビリティ**: スレッド数やリソースを柔軟に調整可能。（クラウドネイティブを考慮し環境変数化することが個人的に望ましい）
- **例外ハンドリング**: 安全なエラーハンドリングで処理の信頼性を向上。
- **ログ管理**: 処理状況をロギングする仕組みを実装。

## 使用技術

- **Java**: 17以上 (jenv使用)
- **依存ライブラリ**: 
  - Lombok (コード簡素化のため)
  - SLF4J (ロギング用)

## セットアップ

### 必要条件
- Java 17以上がインストールされていること
- Gradle のインストール

### 初期構築 (for MacOS)
#### Javaのインストール
- java17のインストール
```shell
# HomeBrewでjava17をインストール
$ brew install openjdk@17
# Brewの指示に従ってシンボリックリンクの作成
```

- jenvにJava17を登録
```shell
$ jenv add $(/usr/libexec/java_home -v 17)

$ jenv versions
# -> インストールしたjavaのバージョンが表示されれば成功

# java 17を使用するように以下コマンドを実行
# 全体で設定する場合
$ jenv global 17
# プロジェクトごとに設定する場合 (~/.java-version)
$ jenv local 17
```

- 環境変数の設定 (すでにjenvを使用したことがある場合は不要)
```shell
# ~/.zshrc（または ~/.bash_profile）に以下を追加する
export PATH="$HOME/.jenv/bin:$PATH"
eval "$(jenv init -)"
```

- javaが正常に設定されていること確認
```shell
java -version
# 設定したJavaのバージョンが表示されれば設定完了
```

#### Gradle
- Gradleのインストール
```shell
$ brew install gradle

# バージョンが表示されればOK
$ gradle -v
```

### ビルドと実行方法
```shell
# ビルド
$ ./gradlew build

# SpringBootの起動
$ ./gradlew bootRun
```



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
