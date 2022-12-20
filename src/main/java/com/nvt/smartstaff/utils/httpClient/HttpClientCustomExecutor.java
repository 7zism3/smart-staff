package com.nvt.smartstaff.utils.httpClient;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

// Custom Executor + Concurrent Requests
// Trình thực thi tùy chỉnh + Yêu cầu đồng thời
public class HttpClientCustomExecutor {

    // custom executor
    private static final ExecutorService executorService = Executors.newFixedThreadPool(5);

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .executor(executorService)
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static String getHttp(String[] urls) throws Exception {

        List<URI> targets = Arrays.stream(urls).map(url -> URI.create(url))
                .collect(Collectors.toList());

        List<CompletableFuture<String>> result = targets.stream()
                .map(url -> httpClient.sendAsync(
                                HttpRequest.newBuilder(url)
                                        .GET()
                                        .setHeader("User-Agent", "Java 11 HttpClient Bot")
                                        .build(),
                                HttpResponse.BodyHandlers.ofString())
                        .thenApply(response -> response.body()))
                .collect(Collectors.toList());

        String kq = result.stream().map(stringCompletableFuture -> {
                    try {
                        return stringCompletableFuture.get();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.joining(", "));
        return kq;
    }

}
