package com.microhe.stacks.starter.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Instant;

public class Quote implements Serializable {

    private static final long serialVersionUID = 8177813047688798724L;
    private static final MathContext MATH_CONTEXT = new MathContext(2);

    private String ticker;
    private BigDecimal price;
    private Instant instant;

    public Quote() {

    }

    public Quote(String ticker, Double price) {
        this(ticker, new BigDecimal(price, MATH_CONTEXT));
    }

    public Quote(String ticker, BigDecimal price) {
        this.ticker = ticker;
        this.price = price;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    @Override
    public String toString() {
        return "Quote{" + "ticker='" + ticker + '\'' + ", price=" + price + ", instant=" + instant
                + '}';
    }

}
