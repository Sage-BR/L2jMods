//================================================================================
// AutoPlay.
//================================================================================

class AutoPlay extends UICommonAPI;

var WindowHandle Me;
var ButtonHandle Btnattack;
var ButtonHandle Btnattackoff;
var ButtonHandle Btnattackoffexpand;

var ButtonHandle botonm;
var ButtonHandle botone;
var ButtonHandle BtnLongRange;
var ButtonHandle BtnShortRange;
var ButtonHandle BtnRespectHunt;
var ButtonHandle BtnSummon;
var AnimTextureHandle m_TexAnim1;
var AnimTextureHandle m_TexAnim2;
var AutoSkillWnd m_scriptAutoSkillWnd; 
var bool b_SkillsShow;  
var AnimTextureHandle m_TexAnim1Expand;
var AnimTextureHandle m_TexAnim2Expand;
var bool m_IsAnimationPlaying;
var bool m_IsAnimationPlayingExpand;
var bool btnRespectHuntIcon;
var bool btnSummontIcon;
var WindowHandle m_ResultAnimation1;
var WindowHandle m_ResultAnimation2;
var WindowHandle m_ResultAnimation1Expand;
var WindowHandle m_ResultAnimation2Expand;
var WindowHandle bajar;
var WindowHandle subir;
    



var bool isMinimize;




function OnLoad() 
{

b_SkillsShow = false;
    m_TexAnim1 = AnimTextureHandle( GetHandle( "AutoPlay.AutoPlayMinimized.Anim1Circle.Anim1Minimized" ));
	m_TexAnim2 = AnimTextureHandle( GetHandle( "AutoPlay.AutoPlayMinimized.Anim2Core.Anim2Minimized" ));
	m_TexAnim1Expand = AnimTextureHandle( GetHandle( "AutoPlay.AutoPlayExpand.Anim1CircleExpand.Anim1Expand" ));
	m_TexAnim2Expand = AnimTextureHandle( GetHandle( "AutoPlay.AutoPlayExpand.Anim2CoreExpand.Anim2Expand" ));
	m_IsAnimationPlaying = false;
    btnRespectHuntIcon = false;
m_scriptAutoSkillWnd = AutoSkillWnd(GetScript("AutoSkillWnd"));   
 m_scriptAutoSkillWnd.cycleON = false; 
	btnSummontIcon = false;
	m_IsAnimationPlayingExpand = false;

    RegisterEvent( EV_Die );
	RegisterEvent( EV_SystemMessage );

    RegisterEvent( EV_Restart );
	
	

	
    m_ResultAnimation1 = GetHandle( "AutoPlay.AutoPlayMinimized.Anim1Circle");
    m_ResultAnimation2 = GetHandle( "AutoPlay.AutoPlayMinimized.Anim2Core");
	
	    m_ResultAnimation1Expand = GetHandle( "AutoPlay.AutoPlayExpand.Anim1CircleExpand");
    m_ResultAnimation2Expand = GetHandle( "AutoPlay.AutoPlayExpand.Anim2CoreExpand");
  
    Btnattackoff = ButtonHandle ( GetHandle ("AutoPlay.AutoPlayMinimized.Btnattackoff") );
	Btnattackoffexpand = ButtonHandle ( GetHandle ("AutoPlay.AutoPlayExpand.BtnattackoffExpand") );

    botonm = ButtonHandle ( GetHandle ("AutoPlay.AutoPlayMinimized.BtnMinimized") );
	botone = ButtonHandle ( GetHandle ("AutoPlay.AutoPlayExpand.BtnExpand") );


    BtnRespectHunt = ButtonHandle ( GetHandle ("AutoPlay.AutoPlayExpand.BtnRespectHunt") );
	
	BtnSummon = ButtonHandle ( GetHandle ("AutoPlay.AutoPlayExpand.BtnSummon") );
	
    bajar = GetHandle( "AutoPlay.AutoPlayMinimized");
    subir = GetHandle( "AutoPlay.AutoPlayExpand");

	
	

	// Oculta la animación al inicio
       
	   BtnOn();
	   BtnOff();
	   BtnOnExpand();
	   BtnOffExpand();
	   
	   BtnRespectHuntOn();
	   BtnRespectHuntOff();
	   BtnSummonOn();
	   BtnSummonOff();
}

function OnEnterState(name a_PreStateName)
{
    class'UIAPI_WINDOW'.static.SetAnchor("AutoPlay", "", "BottomRight", "BottomRight", -300, -180 );
}

function OnDefaultPosition()
{

	
	class'UIAPI_WINDOW'.static.SetAnchor("AutoPlay", "", "BottomRight", "BottomRight", -300, -180 );
	
}





function OnEvent(int Event_ID, string param)
{
local int SystemMsgIndex;


	 if (Event_ID == EV_Restart)
	{
		OnDie();
		  m_IsAnimationPlaying = false;
  m_IsAnimationPlayingExpand = false;
	}


    if (Event_ID == EV_Die)
    {
        OnDie();
    }
    else if (Event_ID == EV_SystemMessage)
    {
	ParseInt ( param, "Index", SystemMsgIndex );
	
        if (SystemMsgIndex == 2154) // Compara directamente el parámetro con el ID del mensaje
        {
            OnTeleport();

		
		}
		
		 else if (SystemMsgIndex == 2155) // Compara directamente el parámetro con el ID del mensaje
        {
  
        m_TexAnim1.Stop();
        m_TexAnim2.Stop();
        m_IsAnimationPlaying = false;
        BtnOff();
        m_ResultAnimation1.HideWindow();
        m_ResultAnimation2.HideWindow();
 //   m_scriptAutoSkillWnd.cycleON = false;  
	
	
        m_TexAnim1Expand.Stop();
        m_TexAnim2Expand.Stop();
        m_IsAnimationPlayingExpand = false;
        BtnOffExpand();
        m_ResultAnimation1Expand.HideWindow();
        m_ResultAnimation2Expand.HideWindow();
    
        }
		
				 else if (SystemMsgIndex == 2156) // Compara directamente el parámetro con el ID del mensaje
        {
            BtnSummonOn();
			btnSummontIcon = true;
        }
				 else if (SystemMsgIndex == 2157) // Compara directamente el parámetro con el ID del mensaje
        {
            BtnSummonOff();
			btnSummontIcon = false;
        }
				 else if (SystemMsgIndex == 2158) // Compara directamente el parámetro con el ID del mensaje
        {
    
           
				BtnRespectHuntOn();
				
                btnRespectHuntIcon = true;
            
        }
		
		else if (SystemMsgIndex == 2159) // Compara directamente el parámetro con el ID del mensaje
        {
           BtnRespectHuntOff();
		   btnRespectHuntIcon = false;
        }
		
		else if (SystemMsgIndex == 2160) // Compara directamente el parámetro con el ID del mensaje
        {
                m_TexAnim1Expand.SetLoopCount(-1);
                m_TexAnim1Expand.Play();
				m_TexAnim2Expand.SetLoopCount(-1);
                m_TexAnim2Expand.Play();
				BtnOnExpand();
				m_ResultAnimation1Expand.ShowWindow();
				m_ResultAnimation2Expand.ShowWindow();
		//		m_scriptAutoSkillWnd.cycleON = True;  
                m_IsAnimationPlayingExpand = true;
				
				m_TexAnim1.SetLoopCount(-1);
                m_TexAnim1.Play();
				m_TexAnim2.SetLoopCount(-1);
                m_TexAnim2.Play();
				BtnOn();
				m_ResultAnimation1.ShowWindow();
				m_ResultAnimation2.ShowWindow();
				
                m_IsAnimationPlaying = true;
				
        }
		

		
    }
}











function OnShow ()
{
	ResetReady();

}








function ResetReady(){
  m_ResultAnimation1.HideWindow();
  m_ResultAnimation2.HideWindow();
  m_ResultAnimation1Expand.HideWindow();
  m_ResultAnimation2Expand.HideWindow();
  BtnOff();
  BtnSummonOff();
  BtnOffExpand();
  BtnRespectHuntOff();
  bajar.HideWindow();
  subir.ShowWindow();

  
  if(btnSummontIcon){
  
  BtnSummonOn();
  }else{
  BtnSummonOff();
  }
  
    if(btnRespectHuntIcon){
  
  BtnRespectHuntOn();
  }else{
  BtnRespectHuntOff();
  }
  
  
  
  
  
          if (m_IsAnimationPlayingExpand) {
            // Mostrar el botón primero en la ventana de minimizado
            BtnOn();
            
            // Mostrar la animación después del botón
            m_TexAnim1.Stop();
            m_TexAnim2.Stop();
            m_ResultAnimation1.ShowWindow();
            m_ResultAnimation2.ShowWindow();
            m_TexAnim1.SetLoopCount(-1);
            m_TexAnim1.Play();
            m_TexAnim2.SetLoopCount(-1);
            m_TexAnim2.Play();
            
            m_IsAnimationPlaying = true;
        } else {
            m_TexAnim1.Stop();
            m_TexAnim2.Stop();
            m_IsAnimationPlaying = false;
            
            BtnOff();
            m_ResultAnimation1.HideWindow();
            m_ResultAnimation2.HideWindow();
        }
		
		    if (m_IsAnimationPlaying) {
            // Mostrar el botón primero en la ventana de expansión
            BtnOnExpand();
            
            // Mostrar la animación después del botón
            m_TexAnim1Expand.Stop();
            m_TexAnim2Expand.Stop();
            m_ResultAnimation1Expand.ShowWindow();
            m_ResultAnimation2Expand.ShowWindow();
            m_TexAnim1Expand.SetLoopCount(-1);
            m_TexAnim1Expand.Play();
            m_TexAnim2Expand.SetLoopCount(-1);
            m_TexAnim2Expand.Play();
            
            m_IsAnimationPlayingExpand = true;
        } else {
            m_TexAnim1Expand.Stop();
            m_TexAnim2Expand.Stop();
            m_IsAnimationPlayingExpand = false;
            
            BtnOffExpand();
            m_ResultAnimation1Expand.HideWindow();
            m_ResultAnimation2Expand.HideWindow();
        }
		
		

		


}

function OnTeleport()
{

    if (m_IsAnimationPlaying)
    {
        m_TexAnim1.Stop();
        m_TexAnim2.Stop();
        m_IsAnimationPlaying = false;
        BtnOff();
        m_ResultAnimation1.HideWindow();
        m_ResultAnimation2.HideWindow();
    }
	
		else if (m_IsAnimationPlayingExpand)
    {
        m_TexAnim1Expand.Stop();
        m_TexAnim2Expand.Stop();
        m_IsAnimationPlayingExpand = false;
        BtnOffExpand();
        m_ResultAnimation1Expand.HideWindow();
        m_ResultAnimation2Expand.HideWindow();
    }
	
	
}

function OnDie()
{
    if (m_IsAnimationPlaying)
    {
        m_TexAnim1.Stop();
        m_TexAnim2.Stop();
        m_IsAnimationPlaying = false;
        BtnOff();
        m_ResultAnimation1.HideWindow();
        m_ResultAnimation2.HideWindow();
    }
	
		else if (m_IsAnimationPlayingExpand)
    {
        m_TexAnim1Expand.Stop();
        m_TexAnim2Expand.Stop();
        m_IsAnimationPlayingExpand = false;
        BtnOffExpand();
        m_ResultAnimation1Expand.HideWindow();
        m_ResultAnimation2Expand.HideWindow();
    }
}








function BtnOn(){

	Btnattackoff.SetTexture( "L2FARIS_CH3.AutoHuntingWnd.FightON_Auto", "L2FARIS_CH3.AutoHuntingWnd.FightON_Auto", "L2FARIS_CH3.AutoHuntingWnd.FightON_Auto" );

}

function BtnOff(){

	Btnattackoff.SetTexture( "L2FARIS_CH3.AutoHuntingWnd.fightoff", "L2FARIS_CH3.AutoHuntingWnd.fightoff", "L2FARIS_CH3.AutoHuntingWnd.fightoff_auto" );

}

function BtnOnExpand(){

	BtnattackoffExpand.SetTexture( "L2FARIS_CH3.AutoHuntingWnd.FightON_Auto", "L2FARIS_CH3.AutoHuntingWnd.FightON_Auto", "L2FARIS_CH3.AutoHuntingWnd.FightON_Auto" );

}

function BtnOffExpand(){

	BtnattackoffExpand.SetTexture( "L2FARIS_CH3.AutoHuntingWnd.fightoff", "L2FARIS_CH3.AutoHuntingWnd.fightoff", "L2FARIS_CH3.AutoHuntingWnd.fightoff_auto" );

}


function BtnSummonOn(){

	BtnSummon.SetTexture( "l2faris_ch3.autohuntingwnd.raidsbtn-shotd-normalon", "l2faris_ch3.autohuntingwnd.raidsbtn-shotd-normalon", "l2faris_ch3.autohuntingwnd.raidsbtn-shotd-overon" );

}

function BtnSummonOff(){

	BtnSummon.SetTexture( "l2faris_ch3.autohuntingwnd.raidsbtn-shotd-normaloff", "l2faris_ch3.autohuntingwnd.raidsbtn-shotd-normaloff", "l2faris_ch3.autohuntingwnd.raidsbtn-shotd-overoff" );

}


function BtnRespectHuntOn(){

	BtnRespectHunt.SetTexture( "L2FARIS_CH3.AutoHuntingWnd.MannerBTNON_NORMAL", "L2FARIS_CH3.AutoHuntingWnd.MannerBTNON_OVER", "L2FARIS_CH3.AutoHuntingWnd.MannerBTNON_NORMAL" );

}

function BtnRespectHuntOff(){

	BtnRespectHunt.SetTexture( "L2FARIS_CH3.AutoHuntingWnd.mannerbtnoff_normal", "L2FARIS_CH3.AutoHuntingWnd.mannerbtnoff_over", "L2FARIS_CH3.AutoHuntingWnd.mannerbtnoff_normal" );

}

function OpenAutoFarm ()
{
	if ( Class'UIAPI_WINDOW'.static.IsShowWindow("AutoPlay.AutoPlayMinimized") )
	{
		Class'UIAPI_WINDOW'.static.HideWindow("AutoPlay.AutoPlayMinimized");
	} 
}

function OpenSaratest ()
{
	if ( Class'UIAPI_WINDOW'.static.IsShowWindow("AutoPlay.AutoPlayExpand") )
	{
		Class'UIAPI_WINDOW'.static.HideWindow("AutoPlay.AutoPlayExpand");
	} 
}

function ExecuteAutofarmBypass()
{
	local string strBypass;
	strBypass = "_autofarm"; // Bypass to execute
	RequestBypassToServer(strBypass);
}

function ExecuteAutoFarmSettingsBypass()
{
	local string strBypass;
	strBypass = "_infosettings"; // Bypass to execute
	RequestBypassToServer(strBypass);
}
function ExecuteLongRangeBypass()
{
	local string strBypass;
	strBypass = "_radiusAutoFarm inc_radius"; // Bypass to execute
	RequestBypassToServer(strBypass);
}
function ExecuteShortBypass()
{
	local string strBypass;
	strBypass = "_radiusAutoFarm dec_radius"; // Bypass to execute
	RequestBypassToServer(strBypass);
}
function ExecuteRespectHuntBypass()
{
	local string strBypass;
	strBypass = "_enableRespectHunt"; // Bypass to execute
	RequestBypassToServer(strBypass);

}

function ExecuteIncPageBypass()
{
	local string strBypass;
	strBypass = "_pageAutoFarm inc_page"; // Bypass to execute
	RequestBypassToServer(strBypass);

}
function ExecuteDecPageBypass()
{
	local string strBypass;
	strBypass = "_pageAutoFarm dec_page"; // Bypass to execute
	RequestBypassToServer(strBypass);

}


function ExecuteSummonBypass()
{
	local string strBypass;
	strBypass = "_enableSummonAttack"; // Bypass to execute
	RequestBypassToServer(strBypass);

}
function ExecuteBuffBypass()
{
	local string strBypass;
	strBypass = "_enableBuffProtect"; // Bypass to execute
	RequestBypassToServer(strBypass);

}
function ToggleMinimizeExpandWindows(bool isMinimized)
{
    if (isMinimized)
    {
        Achicar();

        if (m_IsAnimationPlayingExpand) {
            // Mostrar el botón primero en la ventana de minimizado
            BtnOn();
            
            // Mostrar la animación después del botón
            m_TexAnim1.Stop();
            m_TexAnim2.Stop();
            m_ResultAnimation1.ShowWindow();
            m_ResultAnimation2.ShowWindow();
            m_TexAnim1.SetLoopCount(-1);
            m_TexAnim1.Play();
            m_TexAnim2.SetLoopCount(-1);
            m_TexAnim2.Play();
            
            m_IsAnimationPlaying = true;
        } else {
            m_TexAnim1.Stop();
            m_TexAnim2.Stop();
            m_IsAnimationPlaying = false;
            
            BtnOff();
            m_ResultAnimation1.HideWindow();
            m_ResultAnimation2.HideWindow();
        }
    }
    else
    {
        Agrandar();

        if (m_IsAnimationPlaying) {
            // Mostrar el botón primero en la ventana de expansión
            BtnOnExpand();
            
            // Mostrar la animación después del botón
            m_TexAnim1Expand.Stop();
            m_TexAnim2Expand.Stop();
            m_ResultAnimation1Expand.ShowWindow();
            m_ResultAnimation2Expand.ShowWindow();
            m_TexAnim1Expand.SetLoopCount(-1);
            m_TexAnim1Expand.Play();
            m_TexAnim2Expand.SetLoopCount(-1);
            m_TexAnim2Expand.Play();
            
            m_IsAnimationPlayingExpand = true;
        } else {
            m_TexAnim1Expand.Stop();
            m_TexAnim2Expand.Stop();
            m_IsAnimationPlayingExpand = false;
            
            BtnOffExpand();
            m_ResultAnimation1Expand.HideWindow();
            m_ResultAnimation2Expand.HideWindow();
        }
    }
}






function Achicar ()
{
    
    bajar.ShowWindow();
    subir.HideWindow();
	
}

function Agrandar ()
{

    bajar.HideWindow();
    subir.ShowWindow();
}


 


function OnClickButton (string strID)
{
	switch (strID)
	{
		case "Btnattack":
		
	
		ExecuteAutoFarmSettingsBypass();
		break;
		case "Btnattackoff":
		
		ExecuteAutofarmBypass();
		

		break;
		
				case "Btnattackoffexpand":
		
		ExecuteAutofarmBypass();
		

		break;
		
		
		case "BtnLongRange":
		ExecuteLongRangeBypass();
	
		break;
		case "BtnShortRange":
		ExecuteShortBypass();
	
		break;
		case "BtnRespectHunt":
		ExecuteRespectHuntBypass();

		break;
		
		
		case "BtnIncPage":
     ExecuteIncPageBypass();
	
		break;
				case "BtnDecPage":
        ExecuteDecPageBypass();
  
	
		break;
		
        case "BtnMinimized":
            isMinimize = true;
            ToggleMinimizeExpandWindows(isMinimize);
            break;

        case "BtnExpand":
            isMinimize = false;
            ToggleMinimizeExpandWindows(isMinimize);
            break;
		
	case "BtnSummon":
     ExecuteSummonBypass();
	
		break;
		case "BtnBuff":
        ExecuteBuffBypass();
  
	
		break;
		
		
		

		default:
	}
	
	
	
	

	
	
	
	
	
	
	
}

