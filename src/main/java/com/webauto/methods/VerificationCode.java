package com.webauto.methods;

/**
 * 1、验证码走验证流程
 * （1）、服务器生成验证码以图形展现在浏览器客户端的同时，将验证码以cookie的形式缓存在浏览器客户端
 * （2）、服务器校验验证码的时候，不管客户端传的是什么数据，都通过
 * （3）、让开发注释掉验证码的几行代码
 * （4）、让开发提供一个万能验证码
 *
 * 2、验证码不走验证流程
 * （1）、浏览器客户端不去请求验证码校验的接口，
 *
 * 验证码是服务器生成，保存在服务器端，用于做校验，防止对网页行暴力破解而进行攻击
 */
public class VerificationCode {
}
