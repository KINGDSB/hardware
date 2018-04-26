package indi.dsb.hardware.common;

import java.util.Collections;
import java.util.List;

public class Page<T> {
    private int draw;
    private long recordsTotal;
    private long recordsFiltered;
    private int index;

    private List<T> data = Collections.EMPTY_LIST;

    public Page() {
        super();
    }

    public Page(int draw, int recordsTotal, int recordsFiltered, List<T> data) {
        this.draw = draw;
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
        this.data = data;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}

