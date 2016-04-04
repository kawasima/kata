# KATA01 - Business rules

http://www.driveplaza.com/traffic/tolls_etc/

ここにある、ETC割引の計算ロジックを実装します。

※ただし、平日朝夕割引は実際には後日還元なのですが、ここでは他の割引と同じく即時適用かつ走行距離による還元率の変化はないものとします。


## 業務ルール

割引には以下の適用要件があります。

### 平日朝夕割引

- 平日「朝:6時〜9時」、「夕:17時〜20時」
- 地方部　
- 当月の利用回数が5回〜9回 30%割引、10回以上 50%割引

### 休日割引

- 普通車、軽自動車等(二輪車)限定
- 土曜・日曜・祝日
- 地方部
- 30%割引

### 深夜割引

- すべての車種
- 毎日0〜４時
- 30%割引

## 問題

上記の業務ルールにしたがい、割引率を計算するインタフェースDiscountServiceを実装して下さい。

```java
public interface DisountService {
    public long calc(HighwayDrive drive);
}
```

走行記録はHighwayDriveクラスで表現され、DiscountService#calcに渡されるものとします。
また、割引率はパーセンテージの正の整数で表現されます。
