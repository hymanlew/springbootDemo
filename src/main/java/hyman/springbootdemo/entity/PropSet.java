package hyman.springbootdemo.entity;

/**
 * 用于mybatis 多条件查询时，存放查询条件
 * @author caowanjiang
 *
 */
public class PropSet {
	private Object obj1;
	private Object obj2;
	private Object obj3;
	private Object obj4;
	private Object obj5;
	private Object obj6;
	private Object obj7;
	private Object obj8;
	private Object obj9;
	private Object obj10;
	private Object obj11;
	private Object obj12;
	private Object obj13;
	private Object obj14;
	private Object obj15;
	private Object obj16;
	private Object obj17;
	private Object obj18;
	private Integer start;
	private Integer end;
	public Object getObj1() {
		return obj1;
	}
	public void setObj1(Object obj1) {
		this.obj1 = obj1;
	}
	public Object getObj2() {
		return obj2;
	}
	public void setObj2(Object obj2) {
		this.obj2 = obj2;
	}
	public Object getObj3() {
		return obj3;
	}
	public void setObj3(Object obj3) {
		this.obj3 = obj3;
	}
	public Object getObj4() {
		return obj4;
	}
	public void setObj4(Object obj4) {
		this.obj4 = obj4;
	}
	public Object getObj5() {
		return obj5;
	}
	public void setObj5(Object obj5) {
		this.obj5 = obj5;
	}
	public Object getObj6() {
		return obj6;
	}
	public void setObj6(Object obj6) {
		this.obj6 = obj6;
	}
	public Object getObj7() {
		return obj7;
	}
	public void setObj7(Object obj7) {
		this.obj7 = obj7;
	}
	public Object getObj8() {
		return obj8;
	}
	public void setObj8(Object obj8) {
		this.obj8 = obj8;
	}
	public Object getObj9() {
		return obj9;
	}
	public void setObj9(Object obj9) {
		this.obj9 = obj9;
	}

	public Object getObj10() {
		return obj10;
	}

	public void setObj10(Object obj10) {
		this.obj10 = obj10;
	}

	public Object getObj11() {
		return obj11;
	}

	public void setObj11(Object obj11) {
		this.obj11 = obj11;
	}

	public Object getObj12() {
		return obj12;
	}

	public void setObj12(Object obj12) {
		this.obj12 = obj12;
	}

	public Object getObj13() {
		return obj13;
	}

	public void setObj13(Object obj13) {
		this.obj13 = obj13;
	}

	public Object getObj14() {
		return obj14;
	}

	public void setObj14(Object obj14) {
		this.obj14 = obj14;
	}

	public Object getObj15() {
		return obj15;
	}

	public void setObj15(Object obj15) {
		this.obj15 = obj15;
	}

	public Object getObj16() {
		return obj16;
	}

	public void setObj16(Object obj16) {
		this.obj16 = obj16;
	}

	public Object getObj17() {
		return obj17;
	}

	public void setObj17(Object obj17) {
		this.obj17 = obj17;
	}

	public Object getObj18() {
		return obj18;
	}

	public void setObj18(Object obj18) {
		this.obj18 = obj18;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}
	public void setPager(Integer pageNum,Integer pageSize){
		if(pageNum!=null&&pageSize!=null){
			start=(pageNum-1)*pageSize;
			end=pageSize;
		}
	}


}

