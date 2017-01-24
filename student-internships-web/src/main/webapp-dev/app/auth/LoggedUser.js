
var LoggedUser = function(user, userType) {
    this.user = user;
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

LoggedUser.prototype.getUser = function() {
    return this.user;
}
