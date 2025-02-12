package com.sist.dao;
/*
 * EMPNO	NUMBER(4,0)
ENAME	VARCHAR2(10 BYTE)
JOB	VARCHAR2(9 BYTE)
MGR	NUMBER(4,0)
HIREDATE	DATE
SAL	NUMBER(7,2)
COMM	NUMBER(7,2)
DEPTNO	NUMBER(2,0)
 */
import java.util.*;
import lombok.Data;

@Data
public class EmpVO {
	private int empno,comm,deptno,sal,mgr;
	private String ename,job;
	private Date hiredate;
}
