package io.vertx.starter;

import io.vertx.core.*;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Future<Void> startFuture) throws Exception {
    Vertx vertx = Vertx.vertx();
    vertx.createHttpServer().requestHandler(req -> req.response()
      .putHeader("content-type", "text/plain")
      .end("Hello from Vert.x!")).listen(8080, http -> {
      if (http.succeeded()) {
        startFuture.complete();
        System.out.println("HTTP server started on http://localhost:8080");
      } else {
        startFuture.fail(http.cause());
      }
    });
  }

  public static void main(String[] args) throws Exception {
    new MainVerticle().start(new Future<Void>() {
      @Override
      public boolean isComplete() {
        return false;
      }

      @Override
      public Future<Void> setHandler(Handler<AsyncResult<Void>> handler) {
        return null;
      }

      @Override
      public void complete(Void aVoid) {

      }

      @Override
      public void complete() {

      }

      @Override
      public void fail(Throwable throwable) {

      }

      @Override
      public void fail(String s) {

      }

      @Override
      public boolean tryComplete(Void aVoid) {
        return false;
      }

      @Override
      public boolean tryComplete() {
        return false;
      }

      @Override
      public boolean tryFail(Throwable throwable) {
        return false;
      }

      @Override
      public boolean tryFail(String s) {
        return false;
      }

      @Override
      public Void result() {
        return null;
      }

      @Override
      public Throwable cause() {
        return null;
      }

      @Override
      public boolean succeeded() {
        return false;
      }

      @Override
      public boolean failed() {
        return false;
      }

      @Override
      public void handle(AsyncResult<Void> asyncResult) {

      }
    });
  }
}
