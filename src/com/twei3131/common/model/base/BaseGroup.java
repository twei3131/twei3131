package com.twei3131.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseGroup<M extends BaseGroup<M>> extends Model<M> implements IBean {

	public void setGroupId(java.lang.String groupId) {
		set("groupId", groupId);
	}

	public java.lang.String getGroupId() {
		return get("groupId");
	}

	public void setStudentId(java.lang.String studentId) {
		set("studentId", studentId);
	}

	public java.lang.String getStudentId() {
		return get("studentId");
	}

	public void setClassId(java.lang.String classId) {
		set("classId", classId);
	}

	public java.lang.String getClassId() {
		return get("classId");
	}

	public void setTeacherId(java.lang.String teacherId) {
		set("teacherId", teacherId);
	}

	public java.lang.String getTeacherId() {
		return get("teacherId");
	}

	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public void setGroupName(java.lang.String groupName) {
		set("groupName", groupName);
	}

	public java.lang.String getGroupName() {
		return get("groupName");
	}

}
