package com.gz.medicine.yun.doctor.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ChisPrintsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ChisPrintsExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andGuidIsNull() {
            addCriterion("GUID is null");
            return (Criteria) this;
        }

        public Criteria andGuidIsNotNull() {
            addCriterion("GUID is not null");
            return (Criteria) this;
        }

        public Criteria andGuidEqualTo(String value) {
            addCriterion("GUID =", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidNotEqualTo(String value) {
            addCriterion("GUID <>", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidGreaterThan(String value) {
            addCriterion("GUID >", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidGreaterThanOrEqualTo(String value) {
            addCriterion("GUID >=", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidLessThan(String value) {
            addCriterion("GUID <", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidLessThanOrEqualTo(String value) {
            addCriterion("GUID <=", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidLike(String value) {
            addCriterion("GUID like", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidNotLike(String value) {
            addCriterion("GUID not like", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidIn(List<String> values) {
            addCriterion("GUID in", values, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidNotIn(List<String> values) {
            addCriterion("GUID not in", values, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidBetween(String value1, String value2) {
            addCriterion("GUID between", value1, value2, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidNotBetween(String value1, String value2) {
            addCriterion("GUID not between", value1, value2, "guid");
            return (Criteria) this;
        }

        public Criteria andCfguidIsNull() {
            addCriterion("CFGUID is null");
            return (Criteria) this;
        }

        public Criteria andCfguidIsNotNull() {
            addCriterion("CFGUID is not null");
            return (Criteria) this;
        }

        public Criteria andCfguidEqualTo(String value) {
            addCriterion("CFGUID =", value, "cfguid");
            return (Criteria) this;
        }

        public Criteria andCfguidNotEqualTo(String value) {
            addCriterion("CFGUID <>", value, "cfguid");
            return (Criteria) this;
        }

        public Criteria andCfguidGreaterThan(String value) {
            addCriterion("CFGUID >", value, "cfguid");
            return (Criteria) this;
        }

        public Criteria andCfguidGreaterThanOrEqualTo(String value) {
            addCriterion("CFGUID >=", value, "cfguid");
            return (Criteria) this;
        }

        public Criteria andCfguidLessThan(String value) {
            addCriterion("CFGUID <", value, "cfguid");
            return (Criteria) this;
        }

        public Criteria andCfguidLessThanOrEqualTo(String value) {
            addCriterion("CFGUID <=", value, "cfguid");
            return (Criteria) this;
        }

        public Criteria andCfguidLike(String value) {
            addCriterion("CFGUID like", value, "cfguid");
            return (Criteria) this;
        }

        public Criteria andCfguidNotLike(String value) {
            addCriterion("CFGUID not like", value, "cfguid");
            return (Criteria) this;
        }

        public Criteria andCfguidIn(List<String> values) {
            addCriterion("CFGUID in", values, "cfguid");
            return (Criteria) this;
        }

        public Criteria andCfguidNotIn(List<String> values) {
            addCriterion("CFGUID not in", values, "cfguid");
            return (Criteria) this;
        }

        public Criteria andCfguidBetween(String value1, String value2) {
            addCriterion("CFGUID between", value1, value2, "cfguid");
            return (Criteria) this;
        }

        public Criteria andCfguidNotBetween(String value1, String value2) {
            addCriterion("CFGUID not between", value1, value2, "cfguid");
            return (Criteria) this;
        }

        public Criteria andStatIsNull() {
            addCriterion("STAT is null");
            return (Criteria) this;
        }

        public Criteria andStatIsNotNull() {
            addCriterion("STAT is not null");
            return (Criteria) this;
        }

        public Criteria andStatEqualTo(String value) {
            addCriterion("STAT =", value, "stat");
            return (Criteria) this;
        }

        public Criteria andStatNotEqualTo(String value) {
            addCriterion("STAT <>", value, "stat");
            return (Criteria) this;
        }

        public Criteria andStatGreaterThan(String value) {
            addCriterion("STAT >", value, "stat");
            return (Criteria) this;
        }

        public Criteria andStatGreaterThanOrEqualTo(String value) {
            addCriterion("STAT >=", value, "stat");
            return (Criteria) this;
        }

        public Criteria andStatLessThan(String value) {
            addCriterion("STAT <", value, "stat");
            return (Criteria) this;
        }

        public Criteria andStatLessThanOrEqualTo(String value) {
            addCriterion("STAT <=", value, "stat");
            return (Criteria) this;
        }

        public Criteria andStatLike(String value) {
            addCriterion("STAT like", value, "stat");
            return (Criteria) this;
        }

        public Criteria andStatNotLike(String value) {
            addCriterion("STAT not like", value, "stat");
            return (Criteria) this;
        }

        public Criteria andStatIn(List<String> values) {
            addCriterion("STAT in", values, "stat");
            return (Criteria) this;
        }

        public Criteria andStatNotIn(List<String> values) {
            addCriterion("STAT not in", values, "stat");
            return (Criteria) this;
        }

        public Criteria andStatBetween(String value1, String value2) {
            addCriterion("STAT between", value1, value2, "stat");
            return (Criteria) this;
        }

        public Criteria andStatNotBetween(String value1, String value2) {
            addCriterion("STAT not between", value1, value2, "stat");
            return (Criteria) this;
        }

        public Criteria andDatIsNull() {
            addCriterion("DAT is null");
            return (Criteria) this;
        }

        public Criteria andDatIsNotNull() {
            addCriterion("DAT is not null");
            return (Criteria) this;
        }

        public Criteria andDatEqualTo(Date value) {
            addCriterionForJDBCDate("DAT =", value, "dat");
            return (Criteria) this;
        }

        public Criteria andDatNotEqualTo(Date value) {
            addCriterionForJDBCDate("DAT <>", value, "dat");
            return (Criteria) this;
        }

        public Criteria andDatGreaterThan(Date value) {
            addCriterionForJDBCDate("DAT >", value, "dat");
            return (Criteria) this;
        }

        public Criteria andDatGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("DAT >=", value, "dat");
            return (Criteria) this;
        }

        public Criteria andDatLessThan(Date value) {
            addCriterionForJDBCDate("DAT <", value, "dat");
            return (Criteria) this;
        }

        public Criteria andDatLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("DAT <=", value, "dat");
            return (Criteria) this;
        }

        public Criteria andDatIn(List<Date> values) {
            addCriterionForJDBCDate("DAT in", values, "dat");
            return (Criteria) this;
        }

        public Criteria andDatNotIn(List<Date> values) {
            addCriterionForJDBCDate("DAT not in", values, "dat");
            return (Criteria) this;
        }

        public Criteria andDatBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("DAT between", value1, value2, "dat");
            return (Criteria) this;
        }

        public Criteria andDatNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("DAT not between", value1, value2, "dat");
            return (Criteria) this;
        }

        public Criteria andSytIsNull() {
            addCriterion("SYT is null");
            return (Criteria) this;
        }

        public Criteria andSytIsNotNull() {
            addCriterion("SYT is not null");
            return (Criteria) this;
        }

        public Criteria andSytEqualTo(String value) {
            addCriterion("SYT =", value, "syt");
            return (Criteria) this;
        }

        public Criteria andSytNotEqualTo(String value) {
            addCriterion("SYT <>", value, "syt");
            return (Criteria) this;
        }

        public Criteria andSytGreaterThan(String value) {
            addCriterion("SYT >", value, "syt");
            return (Criteria) this;
        }

        public Criteria andSytGreaterThanOrEqualTo(String value) {
            addCriterion("SYT >=", value, "syt");
            return (Criteria) this;
        }

        public Criteria andSytLessThan(String value) {
            addCriterion("SYT <", value, "syt");
            return (Criteria) this;
        }

        public Criteria andSytLessThanOrEqualTo(String value) {
            addCriterion("SYT <=", value, "syt");
            return (Criteria) this;
        }

        public Criteria andSytLike(String value) {
            addCriterion("SYT like", value, "syt");
            return (Criteria) this;
        }

        public Criteria andSytNotLike(String value) {
            addCriterion("SYT not like", value, "syt");
            return (Criteria) this;
        }

        public Criteria andSytIn(List<String> values) {
            addCriterion("SYT in", values, "syt");
            return (Criteria) this;
        }

        public Criteria andSytNotIn(List<String> values) {
            addCriterion("SYT not in", values, "syt");
            return (Criteria) this;
        }

        public Criteria andSytBetween(String value1, String value2) {
            addCriterion("SYT between", value1, value2, "syt");
            return (Criteria) this;
        }

        public Criteria andSytNotBetween(String value1, String value2) {
            addCriterion("SYT not between", value1, value2, "syt");
            return (Criteria) this;
        }

        public Criteria andOrgIsNull() {
            addCriterion("ORG is null");
            return (Criteria) this;
        }

        public Criteria andOrgIsNotNull() {
            addCriterion("ORG is not null");
            return (Criteria) this;
        }

        public Criteria andOrgEqualTo(String value) {
            addCriterion("ORG =", value, "org");
            return (Criteria) this;
        }

        public Criteria andOrgNotEqualTo(String value) {
            addCriterion("ORG <>", value, "org");
            return (Criteria) this;
        }

        public Criteria andOrgGreaterThan(String value) {
            addCriterion("ORG >", value, "org");
            return (Criteria) this;
        }

        public Criteria andOrgGreaterThanOrEqualTo(String value) {
            addCriterion("ORG >=", value, "org");
            return (Criteria) this;
        }

        public Criteria andOrgLessThan(String value) {
            addCriterion("ORG <", value, "org");
            return (Criteria) this;
        }

        public Criteria andOrgLessThanOrEqualTo(String value) {
            addCriterion("ORG <=", value, "org");
            return (Criteria) this;
        }

        public Criteria andOrgLike(String value) {
            addCriterion("ORG like", value, "org");
            return (Criteria) this;
        }

        public Criteria andOrgNotLike(String value) {
            addCriterion("ORG not like", value, "org");
            return (Criteria) this;
        }

        public Criteria andOrgIn(List<String> values) {
            addCriterion("ORG in", values, "org");
            return (Criteria) this;
        }

        public Criteria andOrgNotIn(List<String> values) {
            addCriterion("ORG not in", values, "org");
            return (Criteria) this;
        }

        public Criteria andOrgBetween(String value1, String value2) {
            addCriterion("ORG between", value1, value2, "org");
            return (Criteria) this;
        }

        public Criteria andOrgNotBetween(String value1, String value2) {
            addCriterion("ORG not between", value1, value2, "org");
            return (Criteria) this;
        }

        public Criteria andAccIsNull() {
            addCriterion("ACC is null");
            return (Criteria) this;
        }

        public Criteria andAccIsNotNull() {
            addCriterion("ACC is not null");
            return (Criteria) this;
        }

        public Criteria andAccEqualTo(String value) {
            addCriterion("ACC =", value, "acc");
            return (Criteria) this;
        }

        public Criteria andAccNotEqualTo(String value) {
            addCriterion("ACC <>", value, "acc");
            return (Criteria) this;
        }

        public Criteria andAccGreaterThan(String value) {
            addCriterion("ACC >", value, "acc");
            return (Criteria) this;
        }

        public Criteria andAccGreaterThanOrEqualTo(String value) {
            addCriterion("ACC >=", value, "acc");
            return (Criteria) this;
        }

        public Criteria andAccLessThan(String value) {
            addCriterion("ACC <", value, "acc");
            return (Criteria) this;
        }

        public Criteria andAccLessThanOrEqualTo(String value) {
            addCriterion("ACC <=", value, "acc");
            return (Criteria) this;
        }

        public Criteria andAccLike(String value) {
            addCriterion("ACC like", value, "acc");
            return (Criteria) this;
        }

        public Criteria andAccNotLike(String value) {
            addCriterion("ACC not like", value, "acc");
            return (Criteria) this;
        }

        public Criteria andAccIn(List<String> values) {
            addCriterion("ACC in", values, "acc");
            return (Criteria) this;
        }

        public Criteria andAccNotIn(List<String> values) {
            addCriterion("ACC not in", values, "acc");
            return (Criteria) this;
        }

        public Criteria andAccBetween(String value1, String value2) {
            addCriterion("ACC between", value1, value2, "acc");
            return (Criteria) this;
        }

        public Criteria andAccNotBetween(String value1, String value2) {
            addCriterion("ACC not between", value1, value2, "acc");
            return (Criteria) this;
        }

        public Criteria andDeptIsNull() {
            addCriterion("DEPT is null");
            return (Criteria) this;
        }

        public Criteria andDeptIsNotNull() {
            addCriterion("DEPT is not null");
            return (Criteria) this;
        }

        public Criteria andDeptEqualTo(String value) {
            addCriterion("DEPT =", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptNotEqualTo(String value) {
            addCriterion("DEPT <>", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptGreaterThan(String value) {
            addCriterion("DEPT >", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptGreaterThanOrEqualTo(String value) {
            addCriterion("DEPT >=", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptLessThan(String value) {
            addCriterion("DEPT <", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptLessThanOrEqualTo(String value) {
            addCriterion("DEPT <=", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptLike(String value) {
            addCriterion("DEPT like", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptNotLike(String value) {
            addCriterion("DEPT not like", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptIn(List<String> values) {
            addCriterion("DEPT in", values, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptNotIn(List<String> values) {
            addCriterion("DEPT not in", values, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptBetween(String value1, String value2) {
            addCriterion("DEPT between", value1, value2, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptNotBetween(String value1, String value2) {
            addCriterion("DEPT not between", value1, value2, "dept");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}