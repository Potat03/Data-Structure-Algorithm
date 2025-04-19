/**
 * @author Nicholas Yap Jia Wey
 */
package entity;

import java.io.Serializable;
import java.util.Objects;


public class AssignmentScope implements Serializable, Comparable<AssignmentScope>{
    private String scopeID;
    private String referenceID;
    private String scopeName;

    public AssignmentScope() {
    }

    public AssignmentScope(String scopeID, String referenceID, String scopeName) {
        this.scopeID = scopeID;
        this.referenceID = referenceID;
        this.scopeName = scopeName;
    }

    public AssignmentScope(String scopeName) {
        this.scopeName = scopeName;
    }


    public String getAssignmentTeamID() {
        return scopeID;
    }

    public void setAssignmentTeamID(String scopeID) {
        this.scopeID = scopeID;
    }

    public String getReferenceID() {
        return referenceID;
    }

    public void setReferenceID(String referenceID) {
        this.referenceID = referenceID;
    }

    public String getScopeName() {
        return scopeName;
    }

    public void setScopeName(String scopeName) {
        this.scopeName = scopeName;
    }

    @Override
    public String toString() {
        return "IndividualScope{" + "scopeID=" + scopeID + ", referenceID=" + referenceID + ", scopeName=" + scopeName + '}';
    }

    @Override
    public int compareTo(AssignmentScope o) {
        return this.scopeName.toUpperCase().compareTo(o.scopeName.toUpperCase());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.scopeName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AssignmentScope other = (AssignmentScope) obj;
        return Objects.equals(this.scopeName, other.scopeName);
    }
    
    
}
