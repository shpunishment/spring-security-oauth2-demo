# spring-security-oauth2-demo
### Spring Security OAuth 最新官方已经不再维护，以下内容只用于学习记录。

spring-security-oauth2-demo是使用Spring Security OAuth2对单点登录和登出的实现。

Spring Security OAuth 建立在Spring Security 之上，所以大部分配置还是在Security中，Security完成对用户的认证和授权，OAuth完成单点登录。

### 单点登录和登出
单点登录是有多个子系统，一个认证中心。当访问其中任意一个子系统时，如果发现未登录，就跳到认证中心进行登录，登录完成后再跳回该子系统。
此时再访问其他子系统时，就已经是登录状态了。
单点登出是统一从认证中心登出，登出后各个子系统就无法访问了，需要再次登录。

单点登录主要靠@EnableOAuth2Sso实现，简化了从资源服务器到认证授权服务器的SSO流程，并使用授权码方式获取。

### 总结
1. @EnableOAuth2Sso 
会将资源服务器标记为OAuth 2.0的客户端， 它将负责将资源所有者（最终用户）重定向到用户必须输入其凭据的授权服务器。完成后，用户将被重定向回具有授权码的客户端。然后客户端通过调用授权服务器获取授权代码并将其交换为访问令牌。只有在此之后，客户端才能使用访问令牌调用资源服务器。

2. @EnableResourceServer 
意味着所属的服务需要访问令牌才能处理请求。在调用资源服务器之前，需要先从授权服务器获取访问令牌。

3. 在资源服务器中配置的路径，都会被 OAuth2AuthenticationProcessingFilter 处理，获取token。

4. 之前一直在纠结，客户端获取到了token，为什么在访问 /user 的请求头中并没有Authorization，亦可请求成功。其实都因为Security。没有在资源服务器中配置的路径，登录认证成功后并不需要携带token，而还是使用Security需要的Cookie和Session。

5. 使用Jwt增强token，如果资源服务器没有配置tokenService，就会调用配置的userInfoUri去auth-server获取用户信息；如果资源服务器配置了tokenService，再加上有UserDetails的实现类，可以解析，就不用在调用auth-server的接口。
这里service-1配置了tokenService，service-2没有配置tokenService。
