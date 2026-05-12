package controlador;

import general.Sistema;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.Pais;
import modelo.PaisArreglo;
import modelo.Region;
import vista.frmIniciar;
import vista.frmPais;

public class ControladorPaises {

    private PaisArreglo modelo;
    private frmPais     vista;

    // ── Catálogo completo de países por región ────────────────────────────────
    private static final Map<String, String[]> PAISES_POR_REGION = new HashMap<>();

    static {
        PAISES_POR_REGION.put("AMERICA DEL SUR", new String[]{
            "ARGENTINA", "BOLIVIA", "BRASIL", "CHILE", "COLOMBIA",
            "ECUADOR", "GUYANA", "PARAGUAY", "PERÚ", "SURINAM",
            "TRINIDAD Y TOBAGO", "URUGUAY", "VENEZUELA"
        });

        PAISES_POR_REGION.put("AMERICA DEL NORTE Y CENTRAL", new String[]{
            "ANTIGUA Y BARBUDA", "BAHAMAS", "BARBADOS", "BELICE",
            "CANADÁ", "COSTA RICA", "CUBA", "DOMINICA",
            "EL SALVADOR", "ESTADOS UNIDOS", "GRANADA",
            "GUATEMALA", "HAITÍ", "HONDURAS", "JAMAICA",
            "MÉXICO", "NICARAGUA", "PANAMÁ",
            "REPÚBLICA DOMINICANA", "SAINT KITTS Y NEVIS",
            "SANTA LUCÍA", "SAN VICENTE Y LAS GRANADINAS"
        });

        PAISES_POR_REGION.put("EUROPA", new String[]{
            "ALBANIA", "ALEMANIA", "ANDORRA", "AUSTRIA",
            "BÉLGICA", "BIELORRUSIA", "BOSNIA Y HERZEGOVINA",
            "BULGARIA", "CHIPRE", "CROACIA", "DINAMARCA",
            "ESLOVAQUIA", "ESLOVENIA", "ESPAÑA", "ESTONIA",
            "FINLANDIA", "FRANCIA", "GRECIA", "HUNGRÍA",
            "IRLANDA", "ISLANDIA", "ITALIA", "KOSOVO",
            "LETONIA", "LIECHTENSTEIN", "LITUANIA", "LUXEMBURGO",
            "MALTA", "MOLDAVIA", "MÓNACO", "MONTENEGRO",
            "NORUEGA", "PAÍSES BAJOS", "POLONIA", "PORTUGAL",
            "REINO UNIDO", "REPÚBLICA CHECA", "RUMANÍA",
            "RUSIA", "SAN MARINO", "SERBIA", "SUECIA",
            "SUIZA", "TURQUÍA", "UCRANIA", "VATICANO"
        });

        PAISES_POR_REGION.put("ASIA", new String[]{
            "AFGANISTÁN", "ARABIA SAUDITA", "ARMENIA", "AZERBAIYÁN",
            "BARÉIN", "BANGLADÉS", "BUTÁN", "CAMBOYA",
            "CHINA", "COREA DEL NORTE", "COREA DEL SUR",
            "EMIRATOS ÁRABES UNIDOS", "FILIPINAS", "GEORGIA",
            "INDIA", "INDONESIA", "IRAK", "IRÁN",
            "ISRAEL", "JAPÓN", "JORDANIA", "KAZAJISTÁN",
            "KIRGUISTÁN", "KUWAIT", "LAOS", "LÍBANO",
            "MALASIA", "MALDIVAS", "MONGOLIA", "MYANMAR",
            "NEPAL", "OMÁN", "PAKISTÁN", "PALESTINA",
            "QATAR", "SINGAPUR", "SIRIA", "SRI LANKA",
            "TAILANDIA", "TAIWÁN", "TAYIKISTÁN", "TIMOR ORIENTAL",
            "TURKMENISTÁN", "UZBEKISTÁN", "VIETNAM", "YEMEN"
        });

        PAISES_POR_REGION.put("AFRICA", new String[]{
            "ANGOLA", "ARGELIA", "BENÍN", "BOTSUANA",
            "BURKINA FASO", "BURUNDI", "CABO VERDE", "CAMERÚN",
            "CHAD", "COMORAS", "CONGO", "COSTA DE MARFIL",
            "DJIBOUTI", "EGIPTO", "ERITREA", "ESUATINI",
            "ETIOPÍA", "GABÓN", "GAMBIA", "GHANA",
            "GUINEA", "GUINEA ECUATORIAL", "GUINEA-BISÁU",
            "KENYA", "LESOTO", "LIBERIA", "LIBIA",
            "MADAGASCAR", "MALAUI", "MALÍ", "MARRUECOS",
            "MAURICIO", "MAURITANIA", "MOZAMBIQUE", "NAMIBIA",
            "NÍGER", "NIGERIA", "REPÚBLICA CENTROAFRICANA",
            "REPÚBLICA DEMOCRÁTICA DEL CONGO", "RUANDA",
            "SÃO TOMÉ Y PRÍNCIPE", "SENEGAL", "SEYCHELLES",
            "SIERRA LEONA", "SOMALIA", "SUDÁFRICA", "SUDÁN",
            "SUDÁN DEL SUR", "TANZANIA", "TOGO", "TÚNEZ",
            "UGANDA", "YIBUTI", "ZAMBIA", "ZIMBABUE"
        });

        PAISES_POR_REGION.put("OCEANIA", new String[]{
            "AUSTRALIA", "FIYI", "ISLAS COOK", "ISLAS MARSHALL",
            "ISLAS SALOMÓN", "KIRIBATI", "MICRONESIA",
            "NAURU", "NUEVA ZELANDA", "PALAOS",
            "PAPÚA NUEVA GUINEA", "SAMOA", "TONGA",
            "TUVALU", "VANUATU"
        });

        PAISES_POR_REGION.put("ANTARTIDA", new String[]{
            "TERRITORIO ANTÁRTICO (sin estados soberanos reconocidos)"
        });
    }

    // ── Constructor ───────────────────────────────────────────────────────────
    public ControladorPaises(PaisArreglo modelo, frmPais vista) {
        this.modelo = modelo;
        this.vista  = vista;

        // ── llenar cboRegion desde Sistema.regiones ───────────────────────────
        // El combo viene vacío del diseñador; se llena con las
        // regiones que App.java registró en Sistema.regiones.
        String[] nombresRegiones = Sistema.regiones.getNombres();
        DefaultComboBoxModel<String> modeloRegiones = new DefaultComboBoxModel<>(nombresRegiones);
        vista.cboRegion.setModel(modeloRegiones);

        // Carga inicial del listado de países según la primera región
        actualizarListadoPaises();

        // ── ItemListener en cboRegion ─────────────────────────────────────────
        // Cada vez que el usuario cambie de continente/región, se filtran
        // automáticamente los países en cboPaisesLista y se limpia el nombre.
        this.vista.cboRegion.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    actualizarListadoPaises();
                    vista.txtNombre.setText("");
                }
            }
        });

        // ── Selección de un país en el listado → autocompletar txtNombre ──────
        this.vista.cboPaisesLista.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Object seleccionado = vista.cboPaisesLista.getSelectedItem();
                    if (seleccionado != null && !seleccionado.toString().startsWith("--")) {
                        vista.txtNombre.setText(seleccionado.toString());
                    }
                }
            }
        });

        // ── Botón Mostrar ─────────────────────────────────────────────────────
        this.vista.btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(vista, modelo);
            }
        });

        // ── Botón Agregar (+) ─────────────────────────────────────────────────
        this.vista.btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = vista.txtNombre.getText().trim();
                String region = vista.cboRegion.getSelectedItem().toString();

                System.out.println("Datos del País-> " + nombre + " - " + region
                        + " - " + Sistema.regiones.getRegionxNombre(region));

                if (Sistema.regiones.getRegionxNombre(region) != null && !nombre.isEmpty()) {
                    Pais p = new Pais(nombre, Sistema.regiones.getRegionxNombre(region));
                    modelo.add(p);
                    limpiarControles();
                } else {
                    JOptionPane.showMessageDialog(vista, "Verifique los datos de entrada.");
                }
            }
        });

        // ── Botón Eliminar (-) ────────────────────────────────────────────────
        this.vista.btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica de eliminación existente
                limpiarControles();
            }
        });

        // ── Botón Cerrar ──────────────────────────────────────────────────────
        this.vista.btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.dispose();
                frmIniciar fInicio = new frmIniciar();
                Sistema.conectado.salir();
                ControladorInicio controlador =
                        new ControladorInicio(Sistema.usuarios, fInicio);
                controlador.iniciar();
            }
        });
    }


    private void actualizarListadoPaises() {
        String regionSeleccionada = vista.cboRegion.getSelectedItem() != null
                ? vista.cboRegion.getSelectedItem().toString()
                : "";

        String[] paises = PAISES_POR_REGION.getOrDefault(
                regionSeleccionada, new String[]{"-- Sin países registrados --"});

        // Construir modelo del combo con un ítem guía al inicio
        String[] items = new String[paises.length + 1];
        items[0] = "-- Seleccione un país --";
        System.arraycopy(paises, 0, items, 1, paises.length);

        DefaultComboBoxModel<String> modeloCombo = new DefaultComboBoxModel<>(items);
        vista.cboPaisesLista.setModel(modeloCombo);
        vista.cboPaisesLista.setSelectedIndex(0);
    }

    private void limpiarControles() {
        vista.txtNombre.setText("");
        if (vista.cboPaisesLista.getItemCount() > 0) {
            vista.cboPaisesLista.setSelectedIndex(0);
        }
        actualizarTabla();
    }

    private void actualizarTabla() {
        // PaisArreglo expone los datos a través de getCabecera() y getDatos()
        DefaultTableModel modeloTabla = new DefaultTableModel(
                modelo.getCabecera(), 0);

        String[][] datos = modelo.getDatos();
        for (String[] fila : datos) {
            modeloTabla.addRow(fila);
        }

        vista.tblPaises.setModel(modeloTabla);
    }

    public void iniciar() {
        actualizarTabla();
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }
}