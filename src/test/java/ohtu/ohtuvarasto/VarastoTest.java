package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void varastonTilavuusAlleNollaNiinTilavuusNolla() {
        assertEquals(0, new Varasto(-1.0).getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void varastonLuontiAlkusaldollaOnnistuuKunSaldoPienempiKuinTilavuus() {
        assertEquals(1, new Varasto(10.0, 1.0).getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastonLuontiAlkusaldollaOnnistuuJaSeTaytetaanKunSaldoSuurempiKuinTilavuus() {
        assertEquals(10, new Varasto(10.0, 12.0).getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastonLuontiAlkusaldollaOnnistuuKunTilavuusJaSaldoPienempiKuinNolla() {
        assertEquals(0, new Varasto(-1, -1).getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivisenSaldonLisaaminenEiMuutaSaldoa() {
        double alkuSaldo = varasto.getSaldo();
        varasto.lisaaVarastoon(-2.0);
        assertEquals(alkuSaldo, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivisenSaldonOttaminenEiMuutaSaldoa() {
        double alkuSaldo = varasto.getSaldo();
        varasto.otaVarastosta(-2.0);
        assertEquals(alkuSaldo, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisatessaSaldoonLiikaaVarastoTayttyyJaYlimaaraisetHukkaan() {
        double tilavuus = varasto.getTilavuus();
        varasto.lisaaVarastoon(tilavuus + 10.0);
        assertEquals(tilavuus, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenLiikaaVieKaikenJaNollaaSaldon() {
        varasto.otaVarastosta(varasto.getSaldo() + 10.0);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void toStringMetodiPalauttaaSaldonJaJaljellaOlevanTilan() {
        assertEquals(varasto.toString(), "saldo = " + varasto.getSaldo() + ", vielä tilaa " + varasto.paljonkoMahtuu());
    }
}