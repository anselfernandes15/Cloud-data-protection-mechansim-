<%@include file="header.jsp" %>
<%@include file="navigation.jsp" %>
<%@include file="flash.jsp" %>
<script>
    function validaterf(){  
var name=document.registerf.data[User][username].value;  
var password=document.registerf.data[User][password].value;  
var agev=document.registerf.data[User][age].value;
var loc=document.registerf.data[User][location].value;
var mbno=document.registerf.data[User][phone_number].value;
var phoneno = /^\d{10}$/;
var emailv=document.registerf.data[User][email].value;
var atposition=emailv.indexOf("@");  
var dotposition=emailv.lastIndexOf(".");
var sq1=document.registerf.data[User][question].value;
var sqa1=document.registerf.data[User][answer].value;
var sq2=document.registerf.q2.value;
var sqa2=document.registerf.a2.value;
if (name==null || name==""){  
  alert("Name can't be blank");  
  return false;  
}else if(password.length<6){  
  alert("Password must be at least 6 characters long.");  
  return false;  
  }  
  
if (isNaN(agev)){  
  document.getElementById("numloc").innerHTML="Enter Numeric value only";  
  return false;  
}else{  
  return true;  
  } 
  
  if (loc==null || loc==""){  
  alert("Location can't be blank");
   return false;  }
  
  if((mobno.value.match(phoneno))
        {
      return true;
        }
      else
        {
        alert("Mobile no shoud be 10 digit with no comma, no spaces, no punctuation and there will be no + sign in front the number. ");
        return false;
        }
  
  if (atposition<1 || dotposition<atposition+2 || dotposition+2>=x.length){  
  alert("Please enter a valid e-mail address \n atpostion:"+atposition+"\n dotposition:"+dotposition);  
  return false;  
  }  
  
   if (sq1==null || sq1=="" ||sq2==null || sq2=="" ||){  
  alert("Please select security question");
   return false;  }
  if (sqa1==null || sqa1=="" ||sqa2==null || sqa2=="" ||){  
  alert("Please provide security question answer");
   return false;  }
}  
</script>
<div class="container">
    <div class="row background space20">
        <div class="span6">
            <form name="registerf" class="form-signin" action="register" enctype="multipart/form-data" onsubmit="return validaterf()">

                <h2 class="form-signin-heading">Please Register</h2>
                 <input type="text" class="input-block-level validate[required,min[500],custom[onlyLetterSp]]" placeholder="Username" name="data[User][username]">
                <input type="password" class="input-block-level validate[required,custom[onlyLetterNumber]]" placeholder="Password" name="data[User][password]">
                <input type="text" class="input-block-level validate[required,min[0],custom[onlyNumberSp],maxSize[2]]" placeholder="Age" name="data[User][age]">
                <input type="text" class="input-block-level validate[required,custom[onlyLetterNumber]]" placeholder="Location" name="data[User][location]">
                <input type="text" class="input-block-level validate[required,custom[integer],minSize[10],maxSize[10]]" placeholder="Contact Number" name="data[User][phone_number]">
                <input type="email" class="input-block-level validate[required,min[10],custom[email]]" placeholder="Email ID " name="data[User][email]">
              <select name="data[User][question]">
                    <option value="-1">-Enter security question set1-</option>
                    <option value="What Is Your Favourite Fruit">What is your favourite fruit?</option> 
                    <option value="What Is Your First Teacher Name">What is your first teacher's name?</option> 
                    <option value="What Is Your Pet Name">What is your pet's name?</option> 
                    <option value="What Is Your Favourite Color">What is your mother's maiden name?</option> 
                </select> 
                
                <input type="password" class="input-block-level validate[required,min[10]]" placeholder="Answer " name="data[User][answer]">
  <select name="q2">
                    <option value="-1">-Enter security question set2-</option>
                    <option value="What is your mothers middle name?">What is your mothers middle name?</option>
                    <option value="What is the last 3 digit of your mobile no ?">What is the last 3 digit of your mobile no ?</option>
                    <option value="What is the name of your birth place ?">What is the name of your birth place ?</option>
                    <option value="Which vegetable you do not like at all ?">Which vegetable you do not like at all ?</option>
                </select>

                <input type="password" class="input-block-level validate[required,min[10]]" placeholder="Answer " name="a2">

                <% String msg = request.getParameter("msg2");
                        if (msg != null) {%><%=msg%><%
                           }
                %>    

                <br>
                <button class="btn btn-large btn-primary" type="submit">Register</button>
                <button class="btn btn-large btn-primary" type="reset">Reset</button>
                
            </form>
        </div>


        <div class="span5">
            <h1></h1>
            <h1>Registration</h1>
            <p>Fill in your details to register.All the mentioned fields are mandatory.</p>
            
        </div>
    </div>


</div>
 <br><br>
<%@include file="footer.jsp" %>