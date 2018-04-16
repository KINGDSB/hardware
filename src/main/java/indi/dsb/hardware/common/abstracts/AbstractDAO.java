package indi.dsb.hardware.common.abstracts;

import java.io.Serializable;

/**
 * Created by Administrator on 11/17/2016.
 */
public interface AbstractDAO<T, PK extends Serializable>
        extends UpdateDAO<T, PK> ,
        SelectDAO<T,PK>,
        DeleteDAO<T, PK>,
        InsertDAO<T, PK> {
}