package indi.dsb.hardware.matter.mapper;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import indi.dsb.hardware.matter.entity.Matter;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by Administrator on 2017/12/7.
 */

public interface MatterMapper extends Mapper<Matter> {
	
	/**
	 * @Title findList 
	 * @Description 公用列表查询方法
	 * @param projectId
	 * @param processStatus
	 * @param type
	 * @param grade
	 * @param createdDate
	 * @param feedbackUid
	 * @param keyword
	 * @param queryUserId
	 * @param startNo
	 * @param pageSize
	 * @return
	 */
	@Select("<script>"  
        + " SELECT "  
        + " t1.id, t1.expect_time AS 'expectTime', t1.true_time AS 'trueTime', t1.process_status AS 'processStatus', "  
        + " t1.description, t1.attachment, t1.created_date AS 'createdDate', t1.grade, t2.id AS 'matterType.id', "  
        + " t2.name AS 'matterType.name', t3.id AS 'project.id', "  
        + " t3.name AS 'project.name', t3.version AS 'project.version', "  
        + " t4.id AS 'feedbackUser.id', t4.name AS 'feedbackUser.name', "  
        + " t5.id AS 'productUser.id', t5.name AS 'productUser.name', "  
        + " (SELECT GROUP_CONCAT(name) FROM sys_user WHERE FIND_IN_SET(id, t1.reply_uid)) AS 'replyNames', "
        + " t6.id AS 'createdUser.id', t5.name AS 'createdUser.name' "  
        + " FROM f_matter t1 "  
        + " LEFT JOIN f_matter_type t2 ON t2.id = t1.type AND t2.is_deleted = 0 "
        + " LEFT JOIN f_project t3 ON t3.id = t1.project_id AND t3.is_deleted = 0 " 
        + " LEFT JOIN sys_user t4 ON t4.id = t1.feedback_uid AND t4.is_deleted = 0 " 
        + " LEFT JOIN sys_user t5 ON t5.id = t1.product_uid AND t5.is_deleted = 0 " 
        + " LEFT JOIN sys_user t6 ON t6.id = t1.created_by AND t6.is_deleted = 0 " 
        + " WHERE t1.is_deleted = 0 "
        + "<if test='projectId != null and projectId > 0'>"  
        + "	AND t1.project_id = #{projectId}"  
        + "</if>"
        + "<if test='processStatus != null and processStatus > 0'>"  
        + "	AND t1.process_status = #{processStatus}"  
        + "</if>"
        + "<if test='type != null and type > 0'>"  
        + " AND t1.type = #{type}"  
        + "</if>"
        + "<if test='grade != null and grade > 0'>"  
        + "	AND t1.grade = #{grade}"  
        + "</if>"
        + "<if test='createdDate != null'>"  
        + "	AND DATE_FORMAT(t1.created_date, '%Y-%m-%d') = DATE_FORMAT(#{createdDate}, '%Y-%m-%d')"  
        + "</if>"
        + "<if test='feedbackUid != null and feedbackUid > 0'>"  
        + "	AND t1.feedback_uid = #{feedbackUid}"  
        + "</if>"
        + "<if test='keyword != null'>"  
        + "	AND t1.description LIKE concat('%', #{keyword}, '%')"  
        + "</if>"
        + "<if test='queryUserId != null'>"  
        + "	AND (t1.feedback_uid = #{queryUserId} OR t1.product_uid = #{queryUserId}"
        + " OR t1.created_by = #{queryUserId} OR FIND_IN_SET(#{queryUserId}, reply_uid))"  
        + "</if>"
        + "<if test='sort == null'>"  
        + " ORDER BY t1.created_date DESC"
        + "</if>"
        + "<if test='sort != null'>"  
        + " ORDER BY #{sort}"
        + "</if>"
        + "<if test='startNo != null and pageSize != null'>"  
        + "	LIMIT #{startNo},#{pageSize}"  
        + "</if>"
        + "</script>")
	List<Matter> findList(
		@Param("projectId") Long projectId,
		@Param("processStatus") Integer processStatus,
		@Param("type") Integer type,
		@Param("grade") Integer grade,
		@Param("createdDate") Date createdDate,
		@Param("feedbackUid") Long feedbackUid,
		@Param("keyword") String keyword,
		@Param("queryUserId") Long queryUserId,
		@Param("sort") String sort,
		@Param("startNo") Integer startNo,
		@Param("pageSize") Integer pageSize
	);
	
	/**
	 * @Title findListCount
	 * @Description 公用列表查询方法
	 * @param projectId
	 * @param processStatus
	 * @param type
	 * @param grade
	 * @param createdDate
	 * @param feedbackUid
	 * @param keyword
	 * @param queryUserId
	 * @param startNo
	 * @param pageSize
	 * @return
	 */
	@Select("<script>"  
        + " SELECT count(1) "  
        + " FROM f_matter t1 "  
        + " LEFT JOIN f_matter_type t2 ON t2.id = t1.type AND t2.is_deleted = 0 "
        + " LEFT JOIN f_project t3 ON t3.id = t1.project_id AND t3.is_deleted = 0 " 
        + " LEFT JOIN sys_user t4	ON t4.id = t1.feedback_uid AND t4.is_deleted = 0 " 
        + " LEFT JOIN sys_user t5 ON t5.id = t1.product_uid AND t5.is_deleted = 0 " 
        + " WHERE t1.is_deleted = 0 "
        + "<if test='projectId != null and projectId > 0'>"  
        + "	AND t1.project_id = #{projectId}"  
        + "</if>"
        + "<if test='processStatus != null and processStatus > 0'>"  
        + "	AND t1.process_status = #{processStatus}"  
        + "</if>"
        + "<if test='type != null and type > 0'>"  
        + " AND t1.type = #{type}"  
        + "</if>"
        + "<if test='grade != null and grade > 0'>"  
        + "	AND t1.grade = #{grade}"  
        + "</if>"
        + "<if test='createdDate != null'>"  
        + " AND DATE_FORMAT(t1.created_date, '%Y-%m-%d') = DATE_FORMAT(#{createdDate}, '%Y-%m-%d')"  
        + "</if>"
        + "<if test='feedbackUid != null and feedbackUid > 0'>"  
        + "	AND t1.feedback_uid = #{feedbackUid}"  
        + "</if>"
        + "<if test='keyword != null'>"  
        + "	AND t1.description LIKE concat('%', #{keyword}, '%')"  
        + "</if>"
        + "<if test='queryUserId != null'>"  
        + "	AND (t1.feedback_uid = #{queryUserId} OR t1.product_uid = #{queryUserId}"
        + " OR t1.created_by = #{queryUserId} OR FIND_IN_SET(#{queryUserId}, reply_uid))"  
        + "</if>"
        + "</script>")
	int findListCount(
		@Param("projectId") Long projectId,
		@Param("processStatus") Integer processStatus,
		@Param("type") Integer type,
		@Param("grade") Integer grade,
		@Param("createdDate") Date createdDate,
		@Param("feedbackUid") Long feedbackUid,
		@Param("keyword") String keyword,
		@Param("queryUserId") Long queryUserId
	);
    
    /**
     * @Title getGrade4Matters 
     * @Description 获取需要推送的致命问题
     * @param matter
     * @return
     */
    @Select(" SELECT t1.id, "
        + " t2.id AS 'project.id', t2.name AS 'project.name', "  
        + " t1.product_uid AS 'productUser.id', t3.name AS 'productUser.name', t3.mobile AS 'productUser.mobile' "
        + " FROM f_matter t1 "  
        + " LEFT JOIN f_project t2 ON t2.id = t1.project_id "  
        + " LEFT JOIN sys_user t3 ON t3.id = t1.product_uid "
        + " WHERE t1.grade = 4 AND t1.process_status IN (1, 2) " 
        + " AND " 
        + " ROUND(((TO_SECONDS(t1.expect_time)-TO_SECONDS(t1.created_date))/2+TO_SECONDS(t1.created_date))/3600)=ROUND(TO_SECONDS(NOW())/3600) "
    )
    List<Matter> getGrade4Matters();
    
    /**
     * @Title getMatterStatistics 
     * @Description 获取非致命问题每日统计提示
     * @param matter
     * @return
     */
    @Select(" SELECT t2.name AS 'projectName', t3.mobile AS 'productMobile', COUNT(1) AS 'total' "
        + " FROM f_matter t1 "  
        + " LEFT JOIN f_project t2 ON t2.id = t1.project_id "
        + " LEFT JOIN sys_user t3 ON t3.id = t1.product_uid "  
        + " WHERE t1.grade <> 4 AND t1.process_status IN (1, 2) "  
        + " AND DATEDIFF(from_unixtime((unix_timestamp(t1.expect_time) - unix_timestamp(t1.created_date)) * 0.7 + unix_timestamp(t1.created_date), '%Y-%m-%d'), NOW()) = 0 "
        + " GROUP BY t1.product_uid, t1.project_id " 
    )
    List<Map> getMatterStatistics();
}
