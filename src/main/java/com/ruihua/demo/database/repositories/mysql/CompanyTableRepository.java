package com.ruihua.demo.database.repositories.mysql;

import com.ruihua.demo.database.entities.mysql.CompanyTable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface CompanyTableRepository extends BaseRepository<CompanyTable>
{
	@Modifying( clearAutomatically = true )
	@Transactional
	@Query( "UPDATE CompanyTable c SET " +
		"c.companyName = :company_name, " +
		"c.companyAddress = :company_address, " +
		"c.companyContacts = :company_contacts, " +
		"c.companyDesc = :company_desc " +
		"WHERE c.mid = :mid" )
	int updateCompanyInfo
		(
			@Param("mid") String mid,
			@Param("company_name") String companyName,
			@Param("company_address") String companyAddress,
			@Param("company_contacts") String companyContacts,
			@Param("company_desc") String companyDesc
		);
}