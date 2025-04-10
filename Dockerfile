# 🔧 第一階段：使用 Gradle 官方映像來建構 JAR
FROM gradle:8.5-jdk17 AS builder

# 複製原始碼到容器內部
COPY --chown=gradle:gradle . /home/gradle/project

# 切換到專案目錄
WORKDIR /home/gradle/project

# 確保 gradlew 有執行權限
RUN chmod +x gradlew

# 建構 jar（不跑測試，加快速度）
# Dockerfile（如果 .jar 已經存在）
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]


# ✅ 第二階段：用更小的 JDK 映像來執行 Spring Boot 應用
FROM openjdk:17-jdk-slim

# 切換工作目錄
WORKDIR /app

# 從 builder 階段複製出 jar 檔案（Spring Boot 產物）
COPY --from=builder /home/gradle/project/build/libs/*.jar app.jar

# 開放 Spring Boot 預設埠口
EXPOSE 8080

# 設定啟動指令
ENTRYPOINT ["java", "-jar", "app.jar"]
