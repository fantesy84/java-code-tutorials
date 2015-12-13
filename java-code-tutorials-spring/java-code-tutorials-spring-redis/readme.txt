一. 开发背景
1. 目前redis无原生的集群功能,实现集群的方式是采用一致性哈希分片技术(shared).将不同的key分配到不同的redis-server上,达到横向扩展的目的.
2. 目前spring所提供的spring-data-redis只能构建单一redis-server的客户端连接,无法使用分片技术达到负载均衡的要求.
3. 如果要使用分片技术,需要程序员自己创建分片客户端实例,无法通过简单的配置来创建分片实例.

二. 目标
1. 利用spring抽象出的Cache接口以及CacheManager接口,开发一套高效的,自动的,易于配置的redis-spring整合工具.