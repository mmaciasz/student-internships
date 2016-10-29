
var LoggedUser = function(userName, userType) {
    this.userName = userName;
    this.userType = userType;
}

LoggedUser.prototype.isAdmin = function() {
    return "ADMIN" === this.userType;
}

LoggedUser.prototype.isFirmEmployee = function() {
    return "FIRM_EMPLOYEE" === this.userType;
}

LoggedUser.prototype.isStudent = function() {
    return "STUDENT" === this.userType;
}
