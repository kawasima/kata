# KATA02 - 発注

注文を受け付けたら、注文テーブルにINSERTし、注文した商品の種類に応じて

## 商品

ECサイトでは、自前で在庫管理している商品もあれば、提携先の代理店を担っているものもある。
提携先の注文は、自テーブルにINSERTした後、提携先のAPIをコールし、注文処理を行います。

現在のビジネスルール

- 自社発送先の注文   Orderを保存して終了
- 提携先A社への注文  Orderを保存したら、注文内容をemailでA社に送る。
- 提携先B社への注文  Orderを保存したら、注文内容をB社のAPIを経由して送る。

## 問題

発注処理を実装してください。

```java
public interface OrderService {
    public void save(Order order);
}
```
