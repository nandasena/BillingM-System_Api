package com.createvision.sivilima.tableModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "user_role")
public class UserRole extends BaseObject implements Serializable {

    @Basic
    @Column(name = "role_name")
    private String roleName;

    @Basic
    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userRole")
    private Set<User> userList;
    @Column(name = "role_id")
    private Long roleId;

    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description;}

    public Set<User> getUserList() { return userList; }
    public void setUserList(Set<User> userList) { this.userList = userList; }

    public Long getRoleId() { return roleId; }
    public void setRoleId(Long roleId) { this.roleId = roleId; }
}
