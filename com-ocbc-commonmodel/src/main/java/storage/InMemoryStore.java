package storage;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import jdk.nashorn.internal.ir.FunctionCall;
import models.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InMemoryStore {

    private HashMap<String, Product> productMap = new HashMap<>();

    public InMemoryStore(Vertx vertx, JsonObject config) {

    }

    /**
     * Suitable for `add`, `exists` operation.
     *
     * @param product        product to be saved
     * @param resultHandler async result handler
     */
    protected void executeNoResult(Product product, Handler<AsyncResult<Void>> resultHandler) {
        /*client.getConnection(connHandler(resultHandler, connection -> {
            connection.updateWithParams(sql, params, r -> {
                if (r.succeeded()) {
                    resultHandler.handle(Future.succeededFuture());
                } else {
                    resultHandler.handle(Future.failedFuture(r.cause()));
                }
                connection.close();
            });
        }));*/

        productMap.putIfAbsent(product.getProductId(), product);

    }

    protected <R> void execute(String params, String sql, R ret, Handler<AsyncResult<R>> resultHandler) {
        /*client.getConnection(connHandler(resultHandler, connection -> {
            connection.updateWithParams(sql, params, r -> {
                if (r.succeeded()) {
                    resultHandler.handle(Future.succeededFuture(ret));
                } else {
                    resultHandler.handle(Future.failedFuture(r.cause()));
                }
                connection.close();
            });
        }));*/
    }

    protected <K> Future<Optional<Product>> retrieveOne(String productID) {
        /*return getConnection()
                .compose(connection -> {
                    Future<Optional<JsonObject>> future = Future.future();
                    connection.queryWithParams(sql, new JsonArray().add(param), r -> {
                        if (r.succeeded()) {
                            List<JsonObject> resList = r.result().getRows();
                            if (resList == null || resList.isEmpty()) {
                                future.complete(Optional.empty());
                            } else {
                                future.complete(Optional.of(resList.get(0)));
                            }
                        } else {
                            future.fail(r.cause());
                        }
                        connection.close();
                    });
                    return future;
                });*/

        Future<Optional<Product>> future = Future.future();
        Optional.ofNullable(productMap.getOrDefault(productID, null))
                .ifPresent( x-> future.complete(Optional.of(x)));
        return future;
    }

    protected int calcPage(int page, int limit) {
        if (page <= 0)
            return 0;
        return limit * (page - 1);
    }

    /*protected Future<List<JsonObject>> retrieveByPage(int page, int limit, String sql) {
        JsonArray params = new JsonArray().add(calcPage(page, limit)).add(limit);
        return getConnection().compose(connection -> {
            Future<List<JsonObject>> future = Future.future();
            connection.queryWithParams(sql, params, r -> {
                if (r.succeeded()) {
                    future.complete(r.result().getRows());
                } else {
                    future.fail(r.cause());
                }
                connection.close();
            });
            return future;
        });
    }*/

     /* protected Future<List<JsonObject>> retrieveMany(JsonArray param, String sql) {
      return getConnection().compose(connection -> {
            Future<List<JsonObject>> future = Future.future();
            connection.queryWithParams(sql, param, r -> {
                if (r.succeeded()) {
                    future.complete(r.result().getRows());
                } else {
                    future.fail(r.cause());
                }
                connection.close();
            });
            return future;
        });
    }*/

    protected Future<List<Product>> retrieveAll(String sql) {
        /* return getConnection().compose(connection -> {
           Future<List<JsonObject>> future = Future.future();
            connection.query(sql, r -> {
                if (r.succeeded()) {
                    future.complete(r.result().getRows());
                } else {
                    future.fail(r.cause());
                }
                connection.close();
            });
            return future;
        });*/

        Future<List<Product>> future = Future.future();
        List<Product> productList =  productMap.values().stream().collect(Collectors.toList());
        future.complete(productList);
        return future;
    }

    protected <K> void removeOne(K id,  Handler<AsyncResult<Void>> resultHandler) {
        /*client.getConnection(connHandler(resultHandler, connection -> {
            JsonArray params = new JsonArray().add(id);
            connection.updateWithParams(sql, params, r -> {
                if (r.succeeded()) {
                    resultHandler.handle(Future.succeededFuture());
                } else {
                    resultHandler.handle(Future.failedFuture(r.cause()));
                }
                connection.close();
            });
        }));*/
        if(productMap.containsKey(id)) {
            productMap.remove(id);
            resultHandler.handle(Future.succeededFuture());
        }
        else {
            resultHandler.handle(Future.failedFuture("ID doesn't exist"));
        }

    }

    protected void removeAll(String sql, Handler<AsyncResult<Void>> resultHandler) {
        /*client.getConnection(connHandler(resultHandler, connection -> {
            connection.update(sql, r -> {
                if (r.succeeded()) {
                    resultHandler.handle(Future.succeededFuture());
                } else {
                    resultHandler.handle(Future.failedFuture(r.cause()));
                }
                connection.close();
            });
        }));*/

        productMap.clear();
        resultHandler.handle(Future.succeededFuture());
    }



}
