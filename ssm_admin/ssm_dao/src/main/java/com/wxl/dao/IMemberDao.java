package com.wxl.dao;

import com.wxl.domain.Member;
import org.apache.ibatis.annotations.Select;

/**
 * 会员持久层入口
 */
public interface IMemberDao {

    /**
     * 根据会员id查询会员信息
     * @param MEMBERID
     * @return
     */
    @Select("select * from member where id =#{MEMBERID}")
    Member findMemberById(String MEMBERID);

}
