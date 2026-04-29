package com.pdm.fipr.orderup.dummy

import com.pdm.fipr.orderup.model.Product
import com.pdm.fipr.orderup.model.TipoProducto


val pupusaImg = "https://comedera.com/wp-content/uploads/sites/9/2023/05/Pupusas-de-quesoshutterstock_1803502444.jpg"
val cafeImg   = "https://i.blogs.es/139e0f/cafe-americano2/840_560.jpeg"
val chocoImg  = "https://cocinaconcoqui.com/wp-content/uploads/2025/12/chocolate-calientecasero-500x500.jpg"
val cocaImg   = "https://d23esi1h40dfmi.cloudfront.net/wpcontent/uploads/2025/08/01124509/00732.jpg"

val menu = listOf(
    Product(1,  "Pupusa de queso",              0.75, pupusaImg, TipoProducto.PUPUSA),
    Product(2,  "Pupusa de frijol con queso",   0.75, pupusaImg, TipoProducto.PUPUSA),
    Product(3,  "Pupusa revuelta",              1.00, pupusaImg, TipoProducto.PUPUSA),
    Product(4,  "Pupusa de chicharrón",         1.00, pupusaImg, TipoProducto.PUPUSA),
    Product(5,  "Pupusa de loroco con queso",   1.00, pupusaImg, TipoProducto.PUPUSA),
    Product(6,  "Pupusa de ayote",              0.75, pupusaImg, TipoProducto.PUPUSA),
    Product(7,  "Pupusa de espinaca",           0.85, pupusaImg, TipoProducto.PUPUSA),
    Product(8,  "Pupusa de jalapeño con queso", 1.00, pupusaImg, TipoProducto.PUPUSA),
    Product(9,  "Café",                         1.00, cafeImg,   TipoProducto.BEBIDA),
    Product(10, "Chocolate",                    1.50, chocoImg,  TipoProducto.BEBIDA),
    Product(11, "Coca-Cola",                    1.25, cocaImg,   TipoProducto.BEBIDA)
)