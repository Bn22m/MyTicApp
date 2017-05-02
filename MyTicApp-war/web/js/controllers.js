'use strict';



// main entry page:
function MainController($scope)
{
   // alert("startUpController... :");
}

// shopping department:

function DepartmentController($scope, Cart)
{
    
   // scope init: 
   var dir = "img/products/";
   //$scope.products = [];
   //$scope.price = 299.99;
   $scope.test = [];
   function testp()
   {
       var s = {};
       s.des = dir+"t-mobile-g2";
       s.price = 399.59;
       return s;
   }
   function testp1()
   {
       var s = {};
       s.des = dir+"samsung-galaxy-tab";
       s.price = 299.59;
       return s;
   }
   function testp2()
   {
       var s = {};
       s.des = dir+"motorola-xoom";
       s.price = 199.69;
       return s;
   }
   function testp3()
   {
       var s = {};
       s.des = dir+"dell-streak-7";
       s.price = 279.59;
       return s;
   }
   function testp4()
   {
       var s = {};
       s.des = dir+"motorola-xoom-with-wi-fi";
       s.price = 499.89;
       return s;
   }
        
   //$scope.products.push(dir+"t-mobile-g2");
   //$scope.products.push(dir+"samsung-galaxy-tab");
   //$scope.products.push(dir+"motorola-xoom");  
   //$scope.products.push(dir+"dell-streak-7");
   //$scope.products.push(dir+"motorola-xoom-with-wi-fi");
   //
   //$scope.test.push(3);
   $scope.test.push(testp());
   $scope.test.push(testp1());
   $scope.test.push(testp2());
   $scope.test.push(testp3());
   $scope.test.push(testp4());
   //alert($scope.test[0].des);
   //alert($scope.test[0].price);
    // adds the product to the cart:
    $scope.addToCart = function(product)
    {
        Cart.add(product);
    };
}

// shopping cart:
function CartController($scope, $location, Cart)
{
    
    updateScope();

    // scope update:
    function updateScope()
    {
        $scope.cartContent = Cart.getProducts();
        $scope.total = Cart.getTotal();
        if (Cart.size() === 0)
        {
            $location.path( "/info" );
        }
    }
    
    // removes the product from the cart:
    $scope.remove = function(product)
    {
        Cart.remove(product);
        updateScope();
    };
    
    // empty shopping cart:
    $scope.removeAll = function()
    {
        Cart.removeAll();
        updateScope();
        $location.path( "/help" );
    };
}

// shopping cart status:
function CartStatusController($scope, Cart)
{    
    // listener on the cart content
    Cart.addListener(function()
    {
        updateCartContentScope();
    });
    
    updateCartContentScope();
    
    // scope update:
    function updateCartContentScope()
    {
        if (Cart.size() === 0)
        {
            $scope.content = "Your cart is empty";
        }
        else 
        {
            $scope.content = Cart.size() + " items in the cart";
        }
    }
    
}

////
// checkout process:
function CheckoutController($scope, $location, Cart, Purchase)
{
    
    // init scope:
    $scope.subtotal = Cart.getTotal();
    $scope.surcharge = 9.99;
    $scope.total = 9.99 + parseFloat(Cart.getTotal());
    $scope.purchaseInfo = [0,1,2,3,4,5];
    $scope.ticRef = document.form3.tnumber.value;
    //$scope.orderId = $scope.ticRef;
    var newPurchase = {};
    

    // make purchase:
    $scope.submitPurchase = function(invalid) 
    {
        if (invalid)
        {
            // form is not valid - show validation errors:
            alert("input error.... ");
            $scope.showError = true;
            return;
        }
        // create new Purchase service:
        //var newPurchase = new Purchase();
        //var purchaseList = [];
        //testp();
        var uname = "uname";
        function testp()
        {
        //var newPurchase = {};
        // initialize it with data:
        newPurchase.name = $scope.purchase.name;
        //alert(newPurchase.name);
        document.form3.message.value +="//\n name: "+ newPurchase.name+"\n";
        newPurchase.email = $scope.purchase.email;
        //alert(newPurchase.email);
        document.form3.message.value +="//\n email: "+ newPurchase.email+"\n";
        newPurchase.phone = $scope.purchase.phone;
        //alert(newPurchase.phone);
        document.form3.message.value +="//\n phone: "+ newPurchase.phone+"\n";
        newPurchase.address = $scope.purchase.address;
        //alert(newPurchase.address);
        document.form3.message.value +="//\n adress: "+ newPurchase.address+"\n";
        newPurchase.ccNumber = $scope.purchase.cc;
        uname = $scope.purchase.cc;
        //alert(newPurchase.ccNumber);
        document.form3.message.value +="//\n ac number: "+ newPurchase.ccNumber+"\n";
        newPurchase.cart = Cart.getProducts();
        //alert(newPurchase.cart);
        document.form3.message.value +="//\n cart: "+ newPurchase.cart+"\n";
        newPurchase.surCharge = 9.99;
        //alert(newPurchase.surCharge);
        document.form3.message.value +="//\n sur charge: "+ newPurchase.surCharge+"\n";
        newPurchase.subTotal = $scope.subtotal;
        //alert(newPurchase.subTotal);
        document.form3.message.value +="//\n sub total: "+ newPurchase.subTotal+"\n";
        newPurchase.Total = $scope.total;
        //alert(newPurchase.Total);
        document.form3.message.value +="//\n total: "+ newPurchase.Total+"\n"; 
        newPurchase.refNumber = $scope.ticRef;
        newPurchase.orderId = $scope.ticRef;
        //alert(newPurchase.refNumber);
        document.form3.message.value +="//\n ref number: "+ newPurchase.refNumber+"\n";
        document.form3.message.value +="//\n///////////////Done////////////////////\n";
        return newPurchase;
    }
        $scope.purchaseInfo.push(testp());
        var order = document.form3.message.value;
        $scope.purchaseInfo.push(order);
        alert("////  order info ////\n "+ order +"\n//////////////////");
        Cart.removeAll();
        window.document.close();
        window.open("welcome.jsp?yname=#/orders");
        window.open(" message.jsp?message='"+order+"'&yname='"+uname+"'" );
        $scope.orderId = $scope.ticRef;
        //newPurchase.$save(function success(data)
        //{
        //    // empty the cart:
        //    alert("data = "+data);
        //    Cart.removeAll();
        //    // init scope with purchase confirmation number:
        //    $scope.orderId = data.orderId;
        //    alert("order id : "+$scope.orderId);
        //});
        
    };

    // cancel purchase:
    $scope.cancelPurchase = function()
    {
        Cart.removeAll();
        $location.path( "/info" );
    };
    
    // start shopping again:
    $scope.startShoppingAgain = function()
    {
        $location.path( "/info" );
    };
    
    //new Account:
    $scope.newMember = function(invalid)
    {
        alert("new account.... ");
        if (invalid)
        {
            // form is not valid - show validation errors:
            alert("input error.... ");
            $scope.showError = true;
            //$location.path( "/new" );
            //window.open("welcome.jsp?yname=#new");
            return;
        }
    };
}

 function myCtrl($scope,Cart) 
 {
    $scope.buckList = [];
    var dir = "img/products/";
    //$scope.price = 259.99;
    //$scope.buckList.push("img/products/newInstallation");
    //$scope.buckList.push("img/products/consultation");
    //$scope.buckList.push("img/products/digitalWall");
    //$scope.buckList.push("img/products/softwareAsAService");
    //$scope.buckList.push("img/products/ITSupport");
    //for(var j = 1; j <= 100;j++){
    //  $scope.buckList.push(j);
    //}
    //
   function testp()
    {
       var s = {};
       s.des = dir+"newInstallation";
       s.price = 259.59;
       return s;
    }
   function testp1()
    {
       var s = {};
       s.des = dir+"consultation";
       s.price = 199.59;
       return s;
    }
   function testp2()
    {
       var s = {};
       s.des = dir+"digitalWall";
       s.price = 399.69;
       return s;
    }
   function testp3()
    {
       var s = {};
       s.des = dir+"softwareAsAService";
       s.price = 249.59;
       return s;
    }
   function testp4()
    {
       var s = {};
       s.des = dir+"ITSupport";
       s.price = 149.99;
       return s;
    }
    
   $scope.buckList.push(testp());
   $scope.buckList.push(testp1());
   $scope.buckList.push(testp2());
   $scope.buckList.push(testp3());
   $scope.buckList.push(testp4());
    
   $scope.addToCart = function(service)
    {
        Cart.add(service);
    };
}; 