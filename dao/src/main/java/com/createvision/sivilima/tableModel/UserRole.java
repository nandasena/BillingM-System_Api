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
    @Column(name = "")
    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userRole")
    private Set<User> partReleases;
    @Column(name = "role_id")
    private Long roleId;

    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description;}

    public Set<User> getPartReleases() { return partReleases; }
    public void setPartReleases(Set<User> partReleases) { this.partReleases = partReleases; }

    public Long getRoleId() { return roleId; }
    public void setRoleId(Long roleId) { this.roleId = roleId; }
}
