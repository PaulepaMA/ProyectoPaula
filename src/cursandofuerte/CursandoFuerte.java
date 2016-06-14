
package cursandofuerte;

import com.mysql.jdbc.PreparedStatement;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *Crea la ventana del programa
 * @author Paula
 */
public class CursandoFuerte extends javax.swing.JFrame {
 
    ConexionBD con = new ConexionBD();
    Connection cn = con.conexion();
    
    /**
     * Crea los componentes
     */
    public CursandoFuerte() {
        initComponents();
        mostrarTablaCursos();
        mostrarTablaAlumnos();
        mostrarTablaProfesores();
        mostrarTablaInscritos();
        mostrarComboCategoria();
        mostrarComboCategoriaProf();
        mostrarComboDNIProf();
        mostrarComboDNIAlum();
        mostrarComboDNICodigo();

    }
    
    
     /**
     * Muestra código de curso de la base de datos en el jComboBox
     */
    void mostrarComboDNICodigo(){
    Statement st;
        try{
        st = cn.createStatement();
        ResultSet rs = st.executeQuery("select distinct codigo from curso");
        while(rs.next()){
            this.comboCursoCodigo.addItem(rs.getString("codigo"));
        }

        }catch (SQLException ex){
            Logger.getLogger(CursandoFuerte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     /**
     * Muestra DNI de alumno de la base de datos en el jComboBox
     */
    void mostrarComboDNIAlum(){
    Statement st;
        try{
        st = cn.createStatement();
        ResultSet rs = st.executeQuery("select distinct dniAlumno from alumno");
        while(rs.next()){
            this.comboDNIAlum.addItem(rs.getString("dniAlumno"));
        }

        }catch (SQLException ex){
            Logger.getLogger(CursandoFuerte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
     /**
     * Muestra DNI de profesores de la base de datos en el jComboBox
     */
    void mostrarComboDNIProf(){
    Statement st;
        try{
        st = cn.createStatement();
        ResultSet rs = st.executeQuery("select distinct dniProfesor from profesor");
        while(rs.next()){
            this.comboDNIProf.addItem(rs.getString("dniProfesor"));
        }

        }catch (SQLException ex){
            Logger.getLogger(CursandoFuerte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Muestra las categorias de curso de la base de datos en el jComboBox
     */
    void mostrarComboCategoria(){
    Statement st;
        try{
        st = cn.createStatement();
        ResultSet rs = st.executeQuery("select distinct categoria from curso");
        while(rs.next()){
            this.comboBox12.addItem(rs.getString("categoria"));
        }

        }catch (SQLException ex){
            Logger.getLogger(CursandoFuerte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Muestra las categorias de profesor de la base de datos en el jComboBox
     */
    void mostrarComboCategoriaProf(){
    Statement st;
        try{
        st = cn.createStatement();
        ResultSet rs = st.executeQuery("select distinct campo from profesor");
        while(rs.next()){
            this.comboBoxAñadir.addItem(rs.getString("campo"));
        }

        }catch (SQLException ex){
            Logger.getLogger(CursandoFuerte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    
    /**
     * Muestra la tabla cursos
     */
    void mostrarTablaCursos(){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("DNI Profesor");
        modelo.addColumn("Nombre");
        modelo.addColumn("Categoría");
        modelo.addColumn("Fecha de inicio");
        modelo.addColumn("Duración");
        tablaCursos.setModel(modelo);
        String sql = "SELECT * FROM curso";
        
        String datos[] = new String [6];
        Statement st;
        try{
        st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            datos[0]=rs.getString(1);
            datos[1]=rs.getString(2);
            datos[2]=rs.getString(3);
            datos[3]=rs.getString(4);
            datos[4]=rs.getString(5);
            datos[5]=rs.getString(6);
            modelo.addRow(datos);
        }
        tablaCursos.setModel(modelo);
        }catch (SQLException ex){
            Logger.getLogger(CursandoFuerte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Muestra la tabla alumnos
     */
    void mostrarTablaAlumnos(){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("DNI Alumno");
        modelo.addColumn("Nombre");
        modelo.addColumn("Fecha de inicio");
        modelo.addColumn("Nivel de estudios");
        tablaAlumnos.setModel(modelo);
        String sql = "SELECT * FROM alumno";
        
        String datos[] = new String [4];
        Statement st;
        try{
        st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            datos[0]=rs.getString(1);
            datos[1]=rs.getString(2);
            datos[2]=rs.getString(3);
            datos[3]=rs.getString(4);
            modelo.addRow(datos);
        }
        tablaAlumnos.setModel(modelo);
        }catch (SQLException ex){
            Logger.getLogger(CursandoFuerte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Muestra la tabla inscritos
     */
    void mostrarTablaInscritos(){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Código del curso");
        modelo.addColumn("Nombre del curso");
        modelo.addColumn("Nombre del alumno");
        modelo.addColumn("DNI del alimno");
        tablaInscritos.setModel(modelo);
        String sql = "select codigo, nombre, nombreAlumno, cursan.dniAlumno from curso, alumno, cursan "
                + "where curso.codigo=cursan.codCurso and alumno.dniAlumno=cursan.dniAlumno";
        
        String datos[] = new String [4];
        Statement st;
        try{
        st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            datos[0]=rs.getString(1);
            datos[1]=rs.getString(2);
            datos[2]=rs.getString(3);
            datos[3]=rs.getString(4);
            modelo.addRow(datos);
        }
        tablaInscritos.setModel(modelo);
        }catch (SQLException ex){
            Logger.getLogger(CursandoFuerte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Muestra la tabla profesores
     */
    void mostrarTablaProfesores(){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("DNI Profesor");
        modelo.addColumn("Nombre");
        modelo.addColumn("Fecha de inicio");
        modelo.addColumn("Especialidad");
        tablaProfesores.setModel(modelo);
        String sql = "SELECT * FROM profesor";
        
        String datos[] = new String [4];
        Statement st;
        try{
        st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            datos[0]=rs.getString(1);
            datos[1]=rs.getString(2);
            datos[2]=rs.getString(3);
            datos[3]=rs.getString(4);
            modelo.addRow(datos);
        }
        tablaProfesores.setModel(modelo);
        }catch (SQLException ex){
            Logger.getLogger(CursandoFuerte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu5 = new javax.swing.JMenu();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        menuBar1 = new java.awt.MenuBar();
        menu1 = new java.awt.Menu();
        menu2 = new java.awt.Menu();
        menuBar2 = new java.awt.MenuBar();
        menu3 = new java.awt.Menu();
        menu4 = new java.awt.Menu();
        popupMenu1 = new java.awt.PopupMenu();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaCursos1 = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaCursos2 = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel14 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel15 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        txtNombre13 = new javax.swing.JTextField();
        comboBox12 = new javax.swing.JComboBox();
        txtduracion10 = new javax.swing.JTextField();
        calendario12 = new com.toedter.calendar.JDateChooser();
        jButton14 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaCursos = new javax.swing.JTable();
        botonModificar = new javax.swing.JButton();
        botonActualizar = new javax.swing.JButton();
        codigo = new javax.swing.JTextField();
        botonBorrar = new javax.swing.JButton();
        comboDNIProf = new javax.swing.JComboBox();
        jPanel18 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        txtNombreAlumnoAñadir = new javax.swing.JTextField();
        txtDNIAlumnoAñadir = new javax.swing.JTextField();
        comboBoxAlumnoAñadir = new javax.swing.JComboBox();
        calendarioAlumnoAñadir = new com.toedter.calendar.JDateChooser();
        botonAlumnoAñadir = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tablaAlumnos = new javax.swing.JTable();
        botonAlumnoBorrar = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        txtNombreAñadir = new javax.swing.JTextField();
        txtProfesorAñadir = new javax.swing.JTextField();
        comboBoxAñadir = new javax.swing.JComboBox();
        calendarioAñadir = new com.toedter.calendar.JDateChooser();
        botonAñadirProf = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tablaProfesores = new javax.swing.JTable();
        botonEliminarProf = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        botonDes = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tablaInscritos = new javax.swing.JTable();
        comboCursoCodigo = new javax.swing.JComboBox();
        comboDNIAlum = new javax.swing.JComboBox();

        jMenu5.setText("jMenu5");

        menu1.setLabel("File");
        menuBar1.add(menu1);

        menu2.setLabel("Edit");
        menuBar1.add(menu2);

        menu3.setLabel("File");
        menuBar2.add(menu3);

        menu4.setLabel("Edit");
        menuBar2.add(menu4);

        popupMenu1.setLabel("popupMenu1");

        tablaCursos1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(tablaCursos1);

        tablaCursos2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(tablaCursos2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cursando fuerte");
        setBackground(new java.awt.Color(102, 51, 0));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setSize(new java.awt.Dimension(800, 600));

        jTabbedPane1.setBackground(new java.awt.Color(255, 153, 0));
        jTabbedPane1.setForeground(new java.awt.Color(102, 0, 0));
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N

        jTabbedPane2.setBackground(new java.awt.Color(255, 204, 102));
        jTabbedPane2.setForeground(new java.awt.Color(102, 51, 0));
        jTabbedPane2.setFont(new java.awt.Font("Century Gothic", 1, 10)); // NOI18N

        jPanel15.setBackground(new java.awt.Color(255, 204, 102));

        jLabel62.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel62.setText("DNI del profesor:");

        jLabel63.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel63.setText("Nombre del curso:");

        jLabel64.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel64.setText("Categoría:");

        jLabel65.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel65.setText("Fecha de inicio:");

        jLabel66.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel66.setText("Duración:");

        txtNombre13.setBackground(new java.awt.Color(255, 255, 102));

        comboBox12.setBackground(new java.awt.Color(255, 255, 102));
        comboBox12.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        comboBox12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox12comboBoxActionPerformed(evt);
            }
        });

        txtduracion10.setBackground(new java.awt.Color(255, 255, 102));

        calendario12.setBackground(new java.awt.Color(255, 255, 102));

        jButton14.setBackground(new java.awt.Color(255, 153, 0));
        jButton14.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jButton14.setForeground(new java.awt.Color(51, 0, 0));
        jButton14.setText("Añadir curso");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        tablaCursos.setBackground(new java.awt.Color(255, 255, 102));
        tablaCursos.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        tablaCursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(tablaCursos);

        botonModificar.setBackground(new java.awt.Color(255, 153, 0));
        botonModificar.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        botonModificar.setForeground(new java.awt.Color(51, 0, 0));
        botonModificar.setText("Modificar");
        botonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarActionPerformed(evt);
            }
        });

        botonActualizar.setBackground(new java.awt.Color(255, 153, 0));
        botonActualizar.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        botonActualizar.setForeground(new java.awt.Color(51, 0, 0));
        botonActualizar.setText("Actualizar");
        botonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizarActionPerformed(evt);
            }
        });

        codigo.setBackground(new java.awt.Color(255, 255, 102));

        botonBorrar.setBackground(new java.awt.Color(255, 153, 0));
        botonBorrar.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        botonBorrar.setForeground(new java.awt.Color(51, 0, 0));
        botonBorrar.setText("Borrar");
        botonBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBorrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGap(116, 116, 116)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel66)
                                    .addComponent(jLabel63)
                                    .addComponent(jLabel64)
                                    .addComponent(jLabel65)))
                            .addComponent(jLabel62, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNombre13)
                                    .addComponent(comboBox12, 0, 238, Short.MAX_VALUE)
                                    .addComponent(txtduracion10)
                                    .addComponent(comboDNIProf, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(botonActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(calendario12, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botonModificar)
                                .addGap(18, 18, 18)
                                .addComponent(botonBorrar))))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(jButton14)
                    .addComponent(comboDNIProf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(txtNombre13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonActualizar))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(comboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66)
                    .addComponent(txtduracion10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel65)
                    .addComponent(calendario12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonModificar)
                    .addComponent(botonBorrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        jTabbedPane2.addTab("Curso ", jPanel15);

        jPanel18.setBackground(new java.awt.Color(255, 204, 102));

        jLabel77.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel77.setText("DNI del alumno:");

        jLabel78.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel78.setText("Nombre del alumno:");

        jLabel79.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel79.setText("Nivel de estudios:");

        jLabel80.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel80.setText("Fecha de nacimiento:");

        txtNombreAlumnoAñadir.setBackground(new java.awt.Color(255, 255, 102));

        txtDNIAlumnoAñadir.setBackground(new java.awt.Color(255, 255, 102));
        txtDNIAlumnoAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDNIAlumnoAñadirActionPerformed(evt);
            }
        });

        comboBoxAlumnoAñadir.setBackground(new java.awt.Color(255, 255, 102));
        comboBoxAlumnoAñadir.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        comboBoxAlumnoAñadir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Primaria", "ESO", "FP1", "FP2", "Bachillerato", "Universidad" }));
        comboBoxAlumnoAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxAlumnoAñadircomboBoxActionPerformed(evt);
            }
        });

        calendarioAlumnoAñadir.setBackground(new java.awt.Color(255, 255, 102));

        botonAlumnoAñadir.setBackground(new java.awt.Color(255, 153, 0));
        botonAlumnoAñadir.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        botonAlumnoAñadir.setForeground(new java.awt.Color(51, 0, 0));
        botonAlumnoAñadir.setText("Añadir alumno");
        botonAlumnoAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAlumnoAñadirActionPerformed(evt);
            }
        });

        tablaAlumnos.setBackground(new java.awt.Color(255, 255, 102));
        tablaAlumnos.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        tablaAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane7.setViewportView(tablaAlumnos);

        botonAlumnoBorrar.setBackground(new java.awt.Color(255, 153, 0));
        botonAlumnoBorrar.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        botonAlumnoBorrar.setForeground(new java.awt.Color(51, 0, 0));
        botonAlumnoBorrar.setText("Eliminar alumno");
        botonAlumnoBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAlumnoBorrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel78)
                                    .addComponent(jLabel77)
                                    .addComponent(jLabel79))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNombreAlumnoAñadir)
                                    .addComponent(txtDNIAlumnoAñadir)
                                    .addComponent(comboBoxAlumnoAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel80)
                                .addGap(25, 25, 25)
                                .addComponent(calendarioAlumnoAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(224, 224, 224)
                        .addComponent(botonAlumnoAñadir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonAlumnoBorrar))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel77)
                    .addComponent(txtDNIAlumnoAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel78)
                    .addComponent(txtNombreAlumnoAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel79)
                    .addComponent(comboBoxAlumnoAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel80)
                    .addComponent(calendarioAlumnoAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAlumnoAñadir)
                    .addComponent(botonAlumnoBorrar))
                .addGap(48, 48, 48)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Alumno ", jPanel18);

        jPanel17.setBackground(new java.awt.Color(255, 204, 102));

        jLabel72.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel72.setText("DNI del profesor:");

        jLabel73.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel73.setText("Nombre del profesor:");

        jLabel74.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel74.setText("Categoría:");

        jLabel75.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel75.setText("Fecha de nacimiento:");

        txtNombreAñadir.setBackground(new java.awt.Color(255, 255, 102));
        txtNombreAñadir.setForeground(new java.awt.Color(255, 204, 102));

        txtProfesorAñadir.setBackground(new java.awt.Color(255, 255, 102));
        txtProfesorAñadir.setForeground(new java.awt.Color(255, 204, 102));

        comboBoxAñadir.setBackground(new java.awt.Color(255, 255, 102));
        comboBoxAñadir.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        comboBoxAñadir.setForeground(new java.awt.Color(51, 0, 0));
        comboBoxAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxAñadircomboBoxActionPerformed(evt);
            }
        });

        calendarioAñadir.setForeground(new java.awt.Color(255, 204, 102));

        botonAñadirProf.setBackground(new java.awt.Color(255, 153, 0));
        botonAñadirProf.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        botonAñadirProf.setForeground(new java.awt.Color(51, 0, 0));
        botonAñadirProf.setText("Añadir profesor");
        botonAñadirProf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAñadirProfActionPerformed(evt);
            }
        });

        tablaProfesores.setBackground(new java.awt.Color(255, 255, 102));
        tablaProfesores.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        tablaProfesores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane8.setViewportView(tablaProfesores);

        botonEliminarProf.setBackground(new java.awt.Color(255, 153, 0));
        botonEliminarProf.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        botonEliminarProf.setForeground(new java.awt.Color(51, 0, 0));
        botonEliminarProf.setText("Eliminar profesor");
        botonEliminarProf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarProfActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel73)
                            .addComponent(jLabel72)
                            .addComponent(jLabel74)
                            .addComponent(jLabel75))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtNombreAñadir)
                                .addComponent(txtProfesorAñadir, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                                .addComponent(comboBoxAñadir, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(calendarioAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(219, 219, 219)
                        .addComponent(botonAñadirProf)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonEliminarProf))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel72)
                    .addComponent(txtProfesorAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel73)
                    .addComponent(txtNombreAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74)
                    .addComponent(comboBoxAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel75)
                    .addComponent(calendarioAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAñadirProf)
                    .addComponent(botonEliminarProf))
                .addGap(52, 52, 52)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Profesor ", jPanel17);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 697, Short.MAX_VALUE)
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 523, Short.MAX_VALUE)
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane2))
        );

        jTabbedPane1.addTab("Gestión de datos", jPanel14);

        jPanel13.setBackground(new java.awt.Color(255, 204, 102));

        jLabel60.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel60.setText("Código del curso:");

        jLabel61.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel61.setText("DNI del alumno:");

        jButton13.setBackground(new java.awt.Color(255, 153, 0));
        jButton13.setFont(new java.awt.Font("Century Gothic", 1, 10)); // NOI18N
        jButton13.setForeground(new java.awt.Color(51, 0, 0));
        jButton13.setText("Inscribir ");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        botonDes.setBackground(new java.awt.Color(255, 153, 0));
        botonDes.setFont(new java.awt.Font("Century Gothic", 1, 10)); // NOI18N
        botonDes.setForeground(new java.awt.Color(51, 0, 0));
        botonDes.setText("Desinscribir");
        botonDes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonDesActionPerformed(evt);
            }
        });

        tablaInscritos.setBackground(new java.awt.Color(255, 255, 102));
        tablaInscritos.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        tablaInscritos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane9.setViewportView(tablaInscritos);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel61)
                            .addComponent(jLabel60))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboCursoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboDNIAlum, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(202, 202, 202)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botonDes, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(118, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(comboCursoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel61)
                    .addComponent(comboDNIAlum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton13)
                    .addComponent(botonDes))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Inscripciones", jPanel13);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonAñadirProfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAñadirProfActionPerformed
        añadirProfesor();
    }//GEN-LAST:event_botonAñadirProfActionPerformed

       /**
        * Añade profesores en la tabla profesor de la base de datos
        * @throws HeadlessException 
        */
    public void añadirProfesor() throws HeadlessException {
        try{
            PreparedStatement pps = (PreparedStatement) cn.prepareStatement("INSERT INTO profesor(dniProfesor, nombreProfesor, fechaNacimiento, campo)"
                    + "VALUES (?, ?, ?, ?)");
            pps.setString(1, txtProfesorAñadir.getText());
            pps.setString(2, txtNombreAñadir.getText());
            pps.setDate(3, new java.sql.Date(calendarioAñadir.getDate().getTime()));
            pps.setString(4, comboBoxAñadir.getSelectedItem().toString());
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Datos guardados");
            mostrarTablaProfesores();
        }catch (SQLException ex){
            Logger.getLogger(CursandoFuerte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void comboBoxAñadircomboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxAñadircomboBoxActionPerformed
        
    }//GEN-LAST:event_comboBoxAñadircomboBoxActionPerformed

    private void botonAlumnoAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAlumnoAñadirActionPerformed
        añadirAlumno();

    }//GEN-LAST:event_botonAlumnoAñadirActionPerformed
    /**
     * Añade Alumnos en la tabla alumno de la base de datos
     * @throws HeadlessException 
     */
    public void añadirAlumno() throws HeadlessException {
        try{
            PreparedStatement pps = (PreparedStatement) cn.prepareStatement("INSERT INTO alumno(dniAlumno, nombreAlumno, fechaNacimiento, nivelEstudios)"
                    + "VALUES (?, ?, ?, ?)");
            pps.setString(1, txtDNIAlumnoAñadir.getText());
            pps.setString(2, txtNombreAlumnoAñadir.getText());
            pps.setDate(3, new java.sql.Date(calendarioAlumnoAñadir.getDate().getTime()));
            pps.setString(4, comboBoxAlumnoAñadir.getSelectedItem().toString());
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Datos guardados");
            mostrarTablaAlumnos();
        }catch (SQLException ex){
            Logger.getLogger(CursandoFuerte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void comboBoxAlumnoAñadircomboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxAlumnoAñadircomboBoxActionPerformed
        
    }//GEN-LAST:event_comboBoxAlumnoAñadircomboBoxActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        añadirCurso();

    }//GEN-LAST:event_jButton14ActionPerformed
    /**
     * Añade Cursos en la tabla curso de la base de datos
     * @throws HeadlessException 
     */
    public void añadirCurso() throws HeadlessException {
        try{
            PreparedStatement pps = (PreparedStatement) cn.prepareStatement("INSERT INTO curso(dniProfesor, nombre, categoria, fechaInicio, duracion)"
                    + "VALUES (?, ?, ?, ?, ?)");
            pps.setString(1, comboDNIProf.getSelectedItem().toString());
            pps.setString(2, txtNombre13.getText());
            pps.setString(3, comboBox12.getSelectedItem().toString());
            pps.setDate(4, new java.sql.Date(calendario12.getDate().getTime()));
            pps.setString(5, txtduracion10.getText());
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Datos guardados");
            mostrarTablaCursos();
        }catch (SQLException ex){
            Logger.getLogger(CursandoFuerte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void comboBox12comboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox12comboBoxActionPerformed
        
    }//GEN-LAST:event_comboBox12comboBoxActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        añadirInscripciones();
    }//GEN-LAST:event_jButton13ActionPerformed
    /**
     * Añade inscripciones de alumnos en la tabla cursan de la base de datos
     * @throws HeadlessException 
     */
    public void añadirInscripciones() throws HeadlessException {
        try{
            PreparedStatement pps = (PreparedStatement) cn.prepareStatement("INSERT INTO cursan(dniAlumno, codCurso)"
                    + "VALUES (?, ?)");
            pps.setString(2, comboCursoCodigo.getSelectedItem().toString());
            pps.setString(1, comboDNIAlum.getSelectedItem().toString());
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Datos guardados");
            mostrarTablaInscritos();
        }catch (SQLException ex){
            Logger.getLogger(CursandoFuerte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void botonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarActionPerformed

        modificarCampos();
        

    }//GEN-LAST:event_botonModificarActionPerformed
    /**
     * Inserta los campos de la linea seleccionada en los campos de texto para luego poder modificarlos
     */
    public void modificarCampos() {
        int fila = tablaCursos.getSelectedRow();
        if (fila>=0){
            codigo.setText(tablaCursos.getValueAt(fila, 0).toString());
            comboDNIProf.setSelectedItem(tablaCursos.getValueAt(fila, 1).toString());
            txtNombre13.setText(tablaCursos.getValueAt(fila, 2).toString());
            comboBox12.setSelectedItem(tablaCursos.getValueAt(fila, 3).toString());
            //calendario12.setDate(tablaCursos.getValueAt(fila, 4));
            txtduracion10.setText(tablaCursos.getValueAt(fila, 5).toString());
            
        }
    }

    private void botonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarActionPerformed
       
        modificarCursos();
    }//GEN-LAST:event_botonActualizarActionPerformed
    /**
     * Modifica los campo de la tabla curso de la base de datos según el código del curso seleccionado
     * @throws HeadlessException 
     */
    public void modificarCursos() throws HeadlessException {
        try{
            PreparedStatement pps = (PreparedStatement) cn.prepareStatement("UPDATE curso SET dniProfesor='"+ comboDNIProf.getSelectedItem()+"', "
                    + "nombre='"+txtNombre13.getText()+"', categoria='"+ comboBox12.getSelectedItem()+"', "
                    + "fechaInicio='"+ new java.sql.Date(calendario12.getDate().getTime())+"', duracion='"+txtduracion10.getText()+"'"
                    + "where codigo='"+codigo.getText()+"'" );
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos actualizados");
            mostrarTablaCursos();
            
        }catch(SQLException ex){
            Logger.getLogger(CursandoFuerte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void botonAlumnoBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAlumnoBorrarActionPerformed
        
        eliminarAlumno();
        
    }//GEN-LAST:event_botonAlumnoBorrarActionPerformed
    /**
     * Elimina el alumno seleccionado de la tabla de alumno de la base de datos
     * @throws HeadlessException 
     */
    public void eliminarAlumno() throws HeadlessException {
        int fila=tablaAlumnos.getSelectedRow();
        String valor=tablaAlumnos.getValueAt(fila, 0).toString();
        if(fila>=0){
            try {
                PreparedStatement pps = (PreparedStatement) cn.prepareStatement("DELETE FROM alumno WHERE dniAlumno='"
                        + valor +"'");
                pps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Alumno borrado");
                mostrarTablaAlumnos();
                
            }catch(SQLException ex){
                Logger.getLogger(CursandoFuerte.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void txtDNIAlumnoAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDNIAlumnoAñadirActionPerformed
        
    }//GEN-LAST:event_txtDNIAlumnoAñadirActionPerformed

    private void botonEliminarProfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarProfActionPerformed
        eliminarProfesor();
    }//GEN-LAST:event_botonEliminarProfActionPerformed
    /**
     * Elimina el profesor seleccionado de la tabla profesor de la base de datos
     * @throws HeadlessException 
     */
    public void eliminarProfesor() throws HeadlessException {
        int fila=tablaProfesores.getSelectedRow();
        String valor=tablaProfesores.getValueAt(fila, 0).toString();
        if(fila>=0){
            try {
                PreparedStatement pps = (PreparedStatement) cn.prepareStatement("DELETE FROM profesor WHERE dniProfesor='"
                        + valor +"'");
                pps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Ptofesor borrado");
                mostrarTablaProfesores();
                
            }catch(SQLException ex){
                Logger.getLogger(CursandoFuerte.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void botonBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBorrarActionPerformed
        eliminarCurso();
    }//GEN-LAST:event_botonBorrarActionPerformed
    /**
     * Elimina el curso seleccionado de la tabla curso de la base de datos
     * @throws HeadlessException 
     */
    public void eliminarCurso() throws HeadlessException {
        int fila=tablaCursos.getSelectedRow();
        String valor=tablaCursos.getValueAt(fila, 0).toString();
        if(fila>=0){
            try {
                PreparedStatement pps = (PreparedStatement) cn.prepareStatement("DELETE FROM curso WHERE codigo='"
                        + valor +"'");
                pps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Curso borrado");
                mostrarTablaCursos();
                
            }catch(SQLException ex){
                Logger.getLogger(CursandoFuerte.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void botonDesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDesActionPerformed
        desinscribirAlumno();
    }//GEN-LAST:event_botonDesActionPerformed
    /**
     * Elimina el alumnos seleccionado inscritos de la tabla cursa de la bse de datos
     * @throws HeadlessException 
     */
    public void desinscribirAlumno() throws HeadlessException {
        try{
            PreparedStatement pps = (PreparedStatement) cn.prepareStatement("DELETE FROM cursan WHERE codCurso='"+ comboCursoCodigo.getSelectedItem()+"' and dniAlumno='"
                    +comboDNIAlum.getSelectedItem()+"'");
            
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Alumno desinscrito");
            mostrarTablaInscritos();
            
        }catch(SQLException ex){
            Logger.getLogger(CursandoFuerte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CursandoFuerte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CursandoFuerte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CursandoFuerte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CursandoFuerte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CursandoFuerte().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonActualizar;
    private javax.swing.JButton botonAlumnoAñadir;
    private javax.swing.JButton botonAlumnoBorrar;
    private javax.swing.JButton botonAñadirProf;
    private javax.swing.JButton botonBorrar;
    private javax.swing.JButton botonDes;
    private javax.swing.JButton botonEliminarProf;
    private javax.swing.JButton botonModificar;
    private com.toedter.calendar.JDateChooser calendario;
    private com.toedter.calendar.JDateChooser calendario1;
    private com.toedter.calendar.JDateChooser calendario12;
    private com.toedter.calendar.JDateChooser calendario2;
    private com.toedter.calendar.JDateChooser calendario4;
    private com.toedter.calendar.JDateChooser calendario5;
    private com.toedter.calendar.JDateChooser calendario6;
    private com.toedter.calendar.JDateChooser calendario7;
    private com.toedter.calendar.JDateChooser calendario8;
    private com.toedter.calendar.JDateChooser calendario9;
    private com.toedter.calendar.JDateChooser calendarioAlumnoAñadir;
    private com.toedter.calendar.JDateChooser calendarioAñadir;
    private javax.swing.JTextField codigo;
    private javax.swing.JComboBox comboBox;
    private javax.swing.JComboBox comboBox1;
    private javax.swing.JComboBox comboBox12;
    private javax.swing.JComboBox comboBox2;
    private javax.swing.JComboBox comboBox4;
    private javax.swing.JComboBox comboBox5;
    private javax.swing.JComboBox comboBox6;
    private javax.swing.JComboBox comboBox7;
    private javax.swing.JComboBox comboBox8;
    private javax.swing.JComboBox comboBox9;
    private javax.swing.JComboBox comboBoxAlumnoAñadir;
    private javax.swing.JComboBox comboBoxAñadir;
    private javax.swing.JComboBox comboCursoCodigo;
    private javax.swing.JComboBox comboDNIAlum;
    private javax.swing.JComboBox comboDNIProf;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private java.awt.Menu menu1;
    private java.awt.Menu menu2;
    private java.awt.Menu menu3;
    private java.awt.Menu menu4;
    private java.awt.MenuBar menuBar1;
    private java.awt.MenuBar menuBar2;
    private java.awt.PopupMenu popupMenu1;
    private javax.swing.JTable tablaAlumnos;
    private javax.swing.JTable tablaCursos;
    private javax.swing.JTable tablaCursos1;
    private javax.swing.JTable tablaCursos2;
    private javax.swing.JTable tablaInscritos;
    private javax.swing.JTable tablaProfesores;
    private javax.swing.JTextField txtDNIAlumnoAñadir;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombre1;
    private javax.swing.JTextField txtNombre13;
    private javax.swing.JTextField txtNombre2;
    private javax.swing.JTextField txtNombre4;
    private javax.swing.JTextField txtNombre5;
    private javax.swing.JTextField txtNombre6;
    private javax.swing.JTextField txtNombre7;
    private javax.swing.JTextField txtNombre8;
    private javax.swing.JTextField txtNombre9;
    private javax.swing.JTextField txtNombreAlumnoAñadir;
    private javax.swing.JTextField txtNombreAñadir;
    private javax.swing.JTextField txtProfesor;
    private javax.swing.JTextField txtProfesor1;
    private javax.swing.JTextField txtProfesor2;
    private javax.swing.JTextField txtProfesor4;
    private javax.swing.JTextField txtProfesor5;
    private javax.swing.JTextField txtProfesor6;
    private javax.swing.JTextField txtProfesor7;
    private javax.swing.JTextField txtProfesor8;
    private javax.swing.JTextField txtProfesor9;
    private javax.swing.JTextField txtProfesorAñadir;
    private javax.swing.JTextField txtduracion;
    private javax.swing.JTextField txtduracion1;
    private javax.swing.JTextField txtduracion10;
    private javax.swing.JTextField txtduracion2;
    private javax.swing.JTextField txtduracion4;
    private javax.swing.JTextField txtduracion5;
    private javax.swing.JTextField txtduracion6;
    private javax.swing.JTextField txtduracion7;
    private javax.swing.JTextField txtduracion8;
    private javax.swing.JTextField txtduracion9;
    // End of variables declaration//GEN-END:variables
}
