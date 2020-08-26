package com.ruihua.demo.database.repositories.mysql;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;


@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Long>
{
	public final static String SORT_BY_DEFAULT = "createdAt";

	T findFirstById( java.math.BigInteger id );
	T findFirstByMid( String mid );
	T findFirstByStatusAndMid( int status, String mid  );
}
