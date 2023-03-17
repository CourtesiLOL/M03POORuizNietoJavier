/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.m03pooruiznietojavier;

import com.mycompany.m03pooruiznietojavier.AparellsCasa.Aparell;
import com.mycompany.m03pooruiznietojavier.AparellsCasa.Placa;
import com.mycompany.m03pooruiznietojavier.Casa.Casa;
import com.mycompany.m03pooruiznietojavier.Casa.DBcasa;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author javier
 */
public class M03POORuizNietoJavier {

    public static void main(String[] args) {
        //Instanciamos los objetos y valores que se declaran al iniciar el programa 
        Scanner scan = new Scanner(System.in);
        DBcasa dbcasa = new DBcasa();
        boolean salida = false;
        String comand;

        do {
            //pedimos al usuario el comando a introducir
            System.out.print("> ");
            comand = scan.nextLine();
            //comprobamos si el comando es con mas de un valor y por ende realizamos el split en caso de que sea asi sea
            if (comand.contains(" ")) {
                String[] comandes = comand.split(" ");
                comand = comandes[0];
                //Este primer swich es el que servira para los comandos de mas de un parametro
                switch (comand.toLowerCase()) {
                    //En cada caso de los switch e creado un metodo para tenerlo mas organizado 
                    case "addcasa":
                        addCasa(comandes, dbcasa);
                        break;
                    case "addplaca":
                        addPlaca(comandes, dbcasa);
                        break;
                    case "addaparell":
                        addAparell(comandes, dbcasa);
                        break;
                    case "oncasa":
                        onCasa(comandes, dbcasa);
                        break;
                    case "onaparell":
                        onAparell(comandes, dbcasa);
                        break;
                    case "offaparell":
                        offAparell(comandes, dbcasa);
                        break;
                    case "info":
                        informacio(comandes, dbcasa);
                        break;

                    default:
                        System.out.println("Comanda no reconeguda");
                }
            } else {//En caso de que el cliente solo introduzca un comando sin parametro actuaria este otro swich
                switch (comand.toLowerCase()) {
                    case "list":
                        list(dbcasa);
                        break;
                    case "quit":
                        System.out.println("Asta luego");
                        salida = true;
                        break;
                    default:
                        System.out.println("ERROR: Número de paràmetres incorrecte.");
                        break;

                }
            }

        } while (salida == false);
    }

    private static void addCasa(String[] comand, DBcasa dataBase) {
        //La primera condicio es per comprobar que l'usuari introdueixila cantidad necesaria de parametros
        if (comand.length == 4) {
            //Compruebo que le nif sea correcto
            String comandaSinUltimaLetra = comand[1].substring(0, comand[1].length() - 1);
            if (comand[1].length() == 9 && comandaSinUltimaLetra.matches("\\d+") == true && comand[1].matches("^[0-9]+[a-zA-Z]+$") == true) {
                //Comprueba si existe alguna casa con ese nif
                if (dataBase.getPositionCasa(comand[1]) == -1) {
                    //Comprueba que la superficie sea compuesta por numeros y lo transforma a integer
                    if (comand[3].matches("\\d+") == true) {
                        int num = Integer.parseInt(comand[3]);
                        //En caso de que la superficie sea inferior a 10 m2 mostrara un error
                        if (num > 10) {
                            //creamos la Casa con los parametros correctamente introducidos y se agrega a la ArrayList de DBcasa
                            Casa add = new Casa(comand[1], comand[2], num);
                            dataBase.saveCasa(add);
                            System.out.println("OK: Casa Registrada");

                        } else {
                            System.out.println("ERROR: Superficie incorrecta. Ha de ser més gran de 10");
                        }
                    } else {
                        System.out.println("ERROR: No se a introducido una superficie valida. \n A de ser un numero entero");
                    }
                } else {
                    System.out.println("ERROR: Ja hi ha una casa registrada amb aquest nif");
                }
            } else {
                System.out.println("El nif no es correcto");
            }
        } else {
            System.out.println(" ERROR: Número de paràmetres incorrecte.\\nÚs: addCasa [nif] [nom] [superficie]");
        }

    }

    private static void addPlaca(String[] comand, DBcasa dataBase) {
        //Comparo que los parametros de entrada sean la cantidad correcta 
        if (comand.length == 5) {
            //Compruebo que le nif sea correcto
            String comandaSinUltimaLetra = comand[1].substring(0, comand[1].length() - 1);
            if (comand[1].length() == 9 && comandaSinUltimaLetra.matches("\\d+") == true && comand[1].matches("^[0-9]+[a-zA-Z]+$") == true) {
                //compruebo si existe la casa a la cual quiere agregar la placa
                if (dataBase.getPositionCasa(comand[1]) != -1) {
                    int position = dataBase.getPositionCasa(comand[1]);
                    //comparo que la superficie sea numerica, entera y despues la combierto de string a int
                    if (comand[2].matches("\\d+")) {
                        int superficie = Integer.parseInt(comand[2]);
                        //Comparo que superficie sea mas grande a 0
                        if (superficie > 0) {
                            //comparo que el precio sea numerico, float y despues la combierto de string a float
                            if (comand[3].matches("\\d+(\\.\\d+)") || comand[3].matches("\\d+")) {
                                //Comparo que preu sea mas grande a 0
                                float preu = Float.parseFloat(comand[3]);
                                if (preu > 0) {
                                    //Aqui comparo de la misma forma que el anterior pero con la potencia
                                    if (comand[4].matches("\\d+")) {
                                        int potencia = Integer.parseInt(comand[4]);
                                        if (potencia > 0) {
                                            //Obtengo la casa a la cual corresponde el nif y se la asigno a una variable x
                                            Casa x = dataBase.getUnCasa(position);
                                            //En este if comparo que esa casa tiene suficiente espacio con el metodo 'espacioLibre()'
                                            if (x.espacioLibre(superficie) != true) {
                                                //Y una vez todo se cumple creamos la placa y la agregamos a la arrayList de casa
                                                Placa add = new Placa(superficie, preu, potencia);
                                                x.addplaca(add);
                                                System.out.println("OK: Placa afegida a la casa.");
                                            } else {
                                                System.out.println("ERROR: No hi ha espai disponible per a instal·lar aquesta placa.");
                                            }

                                        } else {
                                            System.out.println("ERROR: La potencia a de ser mes grand de 0");
                                        }
                                    } else {
                                        System.out.println("ERROR: La potencia a de ser un numero enter");
                                    }
                                } else {
                                    System.out.println("ERROR: El preu a de ser mes grand que 0");
                                }
                            } else {
                                System.out.println("ERROR: El preu a de ser numeric ");
                            }
                        } else {
                            System.out.println("ERROR: La superficie a de ser superior a 0");
                        }
                    } else {
                        System.out.println("ERROR: La superficie a de ser un numero enter");
                    }
                } else {
                    System.out.println("ERROR: No existeix ninguna casa amb aquet nif");
                }
            } else {
                System.out.println("El nif introducit no es posible");
            }
        } else {
            System.out.println("ERROR: Número de paràmetres incorrecte.\\nÚs: addPlaca [nif] [superficie] [preu] [potència]");
        }
    }

    private static void addAparell(String[] comand, DBcasa dataBase) {
        //Comprobamos que la casa existe
        int CasaPos = dataBase.getPositionCasa(comand[1]);
        if (CasaPos != -1 && comand.length == 4) {
            Casa x = dataBase.getUnCasa(CasaPos);
            //Comprobamos si ya existe un aparato con la misma descripcion
            if (x.getPositionAparell(comand[2]) == -1) {
                //comprobamos que potencia es numerico y lo trasformamos correspondientemente a un int
                if (comand[3].matches("\\d+") == true) {
                    int potencia = Integer.parseInt(comand[3]);
                    if (potencia > 0) {
                        //Creamos el aparato y lo agregamos a la casa con ese nif
                        Aparell a = new Aparell(comand[2], potencia);
                        x.addaparell(a);
                        System.out.println("OK: Aparell afegit a la casa.");
                    } else {
                        System.out.println("ERROR: Potència incorrecte. Ha de ser més gran de 0.");
                    }

                }

            } else {
                System.out.println("ERROR: Ja existeix un aparell amb aquesta descripció a la casa\n" + "indicada.");
            }
        } else {
            System.out.println("ERROR: Número de paràmetres incorrecte.");
            System.out.println("Ús: addAparell [nif] [descripció] [potència]");
        }

    }

    private static void onCasa(String[] comand, DBcasa dataBase) {
        //Verificamos la existencia de la casa con ese nif
        int CasaPos = dataBase.getPositionCasa(comand[1]);
        if (CasaPos != -1) {
            //Obtenemos la instancia de la casa que a pedido
            Casa x = dataBase.getUnCasa(CasaPos);
            //Si esta apagado la casa se encendera y en caso contrario dira que ya esta encendida
            if (x.getPlomos() == false) {
                x.plomosOn();
                System.out.println("OK: Casa encesa");
            } else {
                System.out.println("ERROR: La casa ja té l'interruptor encès.");
            }
        } else {
            System.out.println("ERROR: No hi ha cap casa registrada amb aquest nif.");
        }
    }

    private static void onAparell(String[] comand, DBcasa dataBase) {
        //comprobamos que introduce los parametros necesarios
        int CasaPos = dataBase.getPositionCasa(comand[1]);
        if (comand.length == 3) {
            //Verifico que el nif que introduce pertenezca a una casa
            if (CasaPos != -1) {
                Casa x = dataBase.getUnCasa(CasaPos);
                //Verificamos si algun aparato existe
                int AparellPos = x.getPositionAparell(comand[2]);
                if (AparellPos != -1) {
                    //Compruebo que los plomos esten encendidos
                    if (x.getPlomos() == true) {
                        System.out.println(x.getPlomos());
                        Aparell y = x.getUnAparell(AparellPos);
                        //Compruebo que la casa este encendida
                        if (y.isEnces() == false) {
                            //comprueba que si lo enciende sobrepasa la potencia total salten els ploms
                            if (sobreCarga(x, y.getPotencia()) == false) {
                                y.onAparell();
                                System.out.println("OK: Aparell ences");
                            }

                        } else {
                            System.out.println("ERROR: L'Aparell ya n'hi es ences");
                        }
                    } else {
                        System.out.println("ERROR: El general de la casa a de estar ences");

                    }

                } else {
                    System.out.println("ERROR: No existeix aquet aparell");
                }
            } else {
                System.out.println("ERROR: No hi ha cap casa registrada amb aquest nif.");
            }
        } else {
            System.out.println("ERROR: Número de paràmetres incorrecte.");
            System.out.println("Ús: onAparell [nif] [descripció aparell]");
        }

    }

    private static void offAparell(String[] comand, DBcasa dataBase) {
        //Verifico que el nif que introduce pertenezca a una casa
        int CasaPos = dataBase.getPositionCasa(comand[1]);
        if (CasaPos != -1) {
            Casa x = dataBase.getUnCasa(CasaPos);
            int AparellPos = x.getPositionAparell(comand[2]);
            //Verifico que el Aparato existe
            if (AparellPos != -1) {
                Aparell y = x.getUnAparell(AparellPos);
                //Compruebo si esta encendido para poder apagarlo
                if (y.isEnces() == true) {
                    y.offAparell();
                    System.out.println("OK: Aparell apagat");
                } else {
                    System.out.println("ERROR: L'Aparell ya n'hi es apagat");
                }

            } else {
                System.out.println("ERROR: No existeix aquet aparell");
            }
        } else {
            System.out.println("ERROR: No hi ha cap casa registrada amb aquest nif.");
        }
    }

    private static void informacio(String[] info, DBcasa dataBase) {
        int x = dataBase.getPositionCasa(info[1]);
        if (x != -1) {
            //Muestra la informacion del una casa en concreta
            Casa y = dataBase.getUnCasa(x);
            System.out.println("--------------------------------");
            System.out.println("Cliente: " + y.getNif() + "-" + y.getNom());
            System.out.println("Plaques solars instal·lades: " + y.getTotPlacas().size());
            System.out.println("Potència total: " + y.PlacaPotenciaTotal() + "W");
            System.out.println("Inversió total: " + y.PlacaPreuTotal() + "€");
            System.out.println("Aparells registrats: " + y.getTotAparells().size());
            System.out.println("Consum actual:  " + y.AparellConsumTotal(0) + "w");
            if (!y.aparellEnces().isEmpty()) {
                ArrayList<Integer> l = y.aparellEnces();
                for (int p : l) {
                    System.out.println("    -" + y.getUnAparell(p).getDescript());
                }
            }
        } else {
            System.out.println("ERROR: El nif no es correcte o la casa no existeix");
        }
    }

    private static void list(DBcasa dataBase) {
        //Rango obtiene la cantidad de casa que existe
        //En cas de no haberi ninguna cara imprimira un error
        int rango = dataBase.getTotCasa().size();
        if (rango > 0) {
            System.out.println("--- Endolls Solars, S.L. ---");
            System.out.println("Cases enregistrades: " + rango);
            for (int i = 0; i < rango; i++) {
                Casa x = dataBase.getUnCasa(i);
                System.out.println("Casa " + (i + 1));
                System.out.println("");
                System.out.println("Client: " + x.getNif() + " - " + x.getNif());
                System.out.println("Superfície de teulada: " + x.getSuperficie());
                System.out.println("Superfície disponible: " + x.teuladaLliure());
                if (!x.getTotPlacas().isEmpty()) {
                    System.out.println("Plaques solars instal·lades: " + x.getTotPlacas().size());
                } else {
                    System.out.println("No té plaques solars instal·lades.");
                }
                if (!x.getTotAparells().isEmpty()) {
                    System.out.println("Aparells registrats: " + x.getTotAparells().size());
                } else {
                    System.out.println("No té cap aparell elèctric registrat.");
                }

                System.out.println("");

            }
        } else {
            System.out.println("No hi ha cases registrades.");
        }

    }

    private static boolean sobreCarga(Casa x, int y) {
        //Cuando el consumo sobrepasa la potencia este metodo apaga la casa y llama al metodo para apagar todos los aparatos
        if (x.AparellConsumTotal(y) > x.PlacaPotenciaTotal()) {
            x.plomosOff();
            x.aparellsOff();
            System.out.println("¡A saltal l'interruptor general de la casa!");
            return true;
        }
        return false;
    }
}
