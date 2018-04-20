package indi.dsb.hardware.product.mapper.sqlprovider;

public class MatterSqlProvider {

	private static final String STATISTICSDATABYUSERID = " SELECT t1.process_status, COUNT(*) number "
			+ " FROM f_matter t1 WHERE t1.is_deleted = 0 "
			+ " AND (t1.feedback_uid = #{queryUserId} OR t1.product_uid = #{queryUserId} "
			+ " OR t1.created_by = #{queryUserId} OR FIND_IN_SET(#{queryUserId}, reply_uid)) "
			+ " GROUP BY t1.process_status ";

	public String statisticsDataByUserId() {
		return STATISTICSDATABYUSERID;
	}

}
