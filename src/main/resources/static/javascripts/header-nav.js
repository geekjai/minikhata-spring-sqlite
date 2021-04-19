/**
 * Display Header Menu
 */
(function ($) {
  'use strict'

  var $mainHeader = $('.main-header')
  $mainHeader.addClass("navbar navbar-expand navbar-white navbar-light");

  var $leftNav = $('<ul />', {
    class: 'navbar-nav'
  })

  $mainHeader.append($leftNav)

  $leftNav.append(
    "<li class='nav-item'><a class='nav-link' data-widget='pushmenu' href='#' role='button'><i class='fas fa-bars'></i></a></li>"
  )
  //Home
  $leftNav.append(
    "<li class='nav-item d-none d-sm-inline-block'><a href='/' class='nav-link'>Home</a></li>"
  )
  //Purchase
  $leftNav.append(
    "<li class='nav-item d-none d-sm-inline-block'><a href='/purchases/viewPurchases' class='nav-link'>Purchase</a></li>"
  )
  //Manufacture
  $leftNav.append(
    "<li class='nav-item d-none d-sm-inline-block'><a href='/manufacture/viewManufactures' class='nav-link'>Manufacture</a></li>"
  )
  //Sell
  $leftNav.append(
    "<li class='nav-item d-none d-sm-inline-block'><a href='/sell/viewSells' class='nav-link'>Sell</a></li>"
  )
  var $rightNav = $('<ul />', {
    class: 'navbar-nav ml-auto'
  })

  $mainHeader.append($rightNav)

  //Documentation
 /*
  $rightNav.append(
    `<li class='nav-item dropdown' style="margin-right:10px;">
      <a class="nav-link bg-info rounded dropdown-toggle" href="#" id="docDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        Documentation
      </a>
      <div class="dropdown-menu py-0" aria-labelledby="docDropdown">
        <a class="dropdown-item" href="/doc/generalui">General Ui</a>
        <div class="dropdown-divider"></div>
        <a class="dropdown-item" href="/doc/generalform">General Form</a>
      </div>
    </li>`
  )*/
  /*
    var $rightNav = $('<ul />', {
      class: 'navbar-nav ml-auto'
    })
    $mainHeader.append($rightNav)
    $rightNav.append(
      "<li class='nav-item'><a class='nav-link' data-widget='control-sidebar' data-slide='true' href='#' role='button'><i class='fas fa-th-large'></i></a></li>"
    )*/
})(jQuery)
