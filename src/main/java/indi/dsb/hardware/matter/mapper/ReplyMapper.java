package indi.dsb.hardware.matter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import indi.dsb.hardware.matter.entity.Reply;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by Administrator on 2017/12/7.
 */

public interface ReplyMapper extends Mapper<Reply> {
    
    /**
     * @Title findList 
     * @Description 查询所有
     * @param matterId
     * @return
     */
    @Select(" SELECT "
        + " t1.id, "
        + " t1.matter_id AS 'matterId', "
        + " t1.type, t1.solution, "
        + " t1.attachment, "
        + " t1.user_id AS 'userId', "
        + " t1.created_date AS 'createdDate', "
        + " t2.id AS 'user.id', "
        + " t2.name AS 'user.name', "
        + " t1.sub_status AS 'subStatus', "
        + " t1.created_date "  
        + " FROM f_reply t1 "
        + " LEFT JOIN sys_user t2 ON t2.id = t1.user_id AND t2.is_deleted = 0 "  
        + " WHERE t1.is_deleted = 0 "
        + " AND t1.matter_id = #{matterId}")
    List<Reply> findList(@Param("matterId") Long matterId);
}
