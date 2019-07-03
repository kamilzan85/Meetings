 var login = $("#login");
 var email = $("#email");
 var password = $("#password");
 var cpassword = $("#confirm_password");
 var usernameRegex = /^[a-zA-Z0-9]{5,20}$/;
 var emailRegex = /^([0-9a-zA-Z]([-.\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\w]*[0-9a-zA-Z]\.)+[a-zA-Z]{2,9})$/;

 /*This regular expression expects atleast 1 small-case letter, 1 Capital letter, 
 1 digit, 1 special character and the length should be between 6-10 characters. 
 The sequence of the characters is not important.*/
 var passwordRegex = /(?=^.{6,15}$)(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&amp;*()_+}{&quot;:;'?/&gt;.&lt;,])(?!.*\s).*$/;

 function checkLogin() {
     var vlogin = login.val();

     if (usernameRegex.test(vlogin)) {
         login.removeClass("invalid");
         login.addClass("valid");
         $("#helper-login").hide();
         checkform();
         return true;
     } else {
         if (vlogin.length < 5 || vlogin.length > 20) {
             $("#helper-login").attr("data-error", "Login must be at least 5 and up to 20 characters long.").show(500);
         } else {
             $("#helper-login").attr("data-error", "Invalid character in login").show(500);
         }
         login.addClass("invalid");
         login.removeClass("valid");
     }
     checkform();
     return false;

 };

 function checkEmail() {
     var vemail = email.val();

     if (emailRegex.test(vemail)) {
         email.removeClass("invalid");
         email.addClass("valid");
         $("#helper-email").hide();
         checkform();
         return true;
     } else {
         $("#helper-email").attr("data-error", "Email is invalid").show();
         email.addClass("invalid");
         email.removeClass("valid");
     }
     checkform();
     return false;

 };

 function checkCPassword() {
     var vcpassword = cpassword.val();
     var vpassword = password.val();

     if (vpassword === vcpassword) {
         $("#helper-cpassword").hide();
         cpassword.removeClass("invalid");
         cpassword.addClass("valid");
         checkform();
         return true;
     } else {
         $("#helper-cpassword").attr("data-error", "Passwords are not the same.").show();
         cpassword.addClass("invalid");
         cpassword.removeClass("valid");
     }
     checkform();
     return false;
 }

 function checkPassword() {
     var vpassword = password.val();
     var vcpassword = cpassword.val();
     if (vcpassword.length != 0) {
         checkCPassword();
     }
     if (passwordRegex.test(vpassword)) {
         password.removeClass("invalid");
         password.addClass("valid");
         $("#helper-password").hide();
         checkform();
         return true;
     } else {
         if (vpassword.length < 6 || vpassword.length > 10) {
             $("#helper-password").attr("data-error", "Length of the password should be between 6-10 characters.").show();
         } else {
             $("#helper-password").attr("data-error", "Password must have at least 1 small-case letter, 1 capital letter and 1 digit.").show();
         }
         password.addClass("invalid");
         password.removeClass("valid");
         checkform();
     }
     return false;
 };

 function checkform() {
     if (login.hasClass("valid") &&
         email.hasClass("valid") &&
         password.hasClass("valid") &&
         cpassword.hasClass("valid")) {
         $("#button-register").attr("disabled", false);
         $("#button-register").removeAttr("disabled");
         return true;
     } else {
         $("#button-register").attr("disabled", true);
     }
     return false;
 };

 $(document).ready(function () {
     if (login.val().length != 0) {
         checkLogin();
     }
     if (email.val().length != 0) {
         checkEmail();
     }
     if (password.val().length != 0) {
         checkPassword();
     }
     if (cpassword.val().length != 0) {
         checkCPassword();
     }
 });

 login.keyup(checkLogin);
 login.change(checkLogin);
 email.keyup(checkEmail);
 email.change(checkEmail);
 cpassword.keyup(checkCPassword);
 cpassword.change(checkCPassword);
 password.keyup(checkPassword);
 password.change(checkPassword);

 $("#register-form").submit(function (e) {
     if (!checkCPassword() || !checkEmail() || !checkLogin() || !checkPassword()) {
         e.preventDefault();
     }
 });