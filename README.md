
# Restaurant

Manuel Felipe Betancur Buitrago (65143)  
Estith Jakobo Álvarez Quintero (64302)

## Descripción General del Sistema
Este proyecto consiste en un sistema de gestión para un restaurante, cuyo objetivo principal es permitir el manejo de mesas, órdenes y productos del menú de manera sencilla.
El sistema facilita a un administrador o mesero realizar acciones como:

- Registrar mesas disponibles y su estado (ocupada/libre).  
- Crear nuevas órdenes asociadas a una mesa.
- Agregar o remover ítems del menú a una orden existente.
- Consultar las órdenes realizadas y sus detalles.
- Calcular totales de la orden según los productos agregados.

## Objetivo
El objetivo del sistema es ofrecer una herramienta práctica para organizar las operaciones básicas del restaurante, manteniendo un registro claro y estructurado de mesas, pedidos y productos.

## Funcionamiento general
- El programa se ejecuta por consola y ofrece un menú interactivo.
- El usuario selecciona una opción y el sistema ejecuta acciones como:
- Crear una mesa o marcarla como ocupada/libre.
- Crear una orden asignada a una mesa.
- Agregar ítems del menú a la orden.
- Eliminar ítems de la orden.
- Mostrar el resumen de la orden.
- Listar el menú o las órdenes existentes.

## Ejemplos de entrada y salida
Registrar un cliente y asignarle una mesa:  
Entradas  
`1-Crear mesa  
Enter number of seats: 4` 

`2-Registrar cliente   
Enter customer name: Hermenegelido  
Enter identification number: 1057783425`  

Salidas  
`Added table: Table ( id=1, seats=4, occupied=false )  
Added: Customer ( id=3, name='Hermenegildo', identificationNumber='1057783425', table=1 )`  





