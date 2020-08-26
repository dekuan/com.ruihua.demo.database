package com.ruihua.demo.database.repositories.redis;


import com.ruihua.demo.database.entities.redis.RedisToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 *	https://www.baeldung.com/spring-data-redis-tutorial
 */
@Repository
public interface RedisTokenRepository extends CrudRepository<RedisToken, String>
{
}