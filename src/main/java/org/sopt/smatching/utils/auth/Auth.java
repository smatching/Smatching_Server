package org.sopt.smatching.utils.auth;

import java.lang.annotation.*;

// 메소드에 적용
@Target(ElementType.METHOD)

//런타임시까지 참조가능
@Retention(RetentionPolicy.RUNTIME)

//Java Doc에도 표시
@Documented

//상속가능
@Inherited


public @interface Auth {
}

