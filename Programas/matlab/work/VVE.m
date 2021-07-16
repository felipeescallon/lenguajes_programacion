function varargout = VVE(varargin)
% VVE M-file for VVE.fig
%      VVE, by itself, creates a new VVE or raises the existing
%      singleton*.
%
%      H = VVE returns the handle to a new VVE or the handle to
%      the existing singleton*.
%
%      VVE('CALLBACK',hObject,eventData,handles,...) calls the local
%      function named CALLBACK in VVE.M with the given input arguments.
%
%      VVE('Property','Value',...) creates a new VVE or raises the
%      existing singleton*.  Starting from the left, property value pairs are
%      applied to the GUI before VVE_OpeningFunction gets called.  An
%      unrecognized property name or invalid value makes property application
%      stop.  All inputs are passed to VVE_OpeningFcn via varargin.
%
%      *See GUI Options on GUIDE's Tools menu.  Choose "GUI allows only one
%      instance to run (singleton)".
%
% See also: GUIDE, GUIDATA, GUIHANDLES

% Edit the above text to modify the response to help VVE

% Last Modified by GUIDE v2.5 17-Feb-2010 10:25:54

% Begin initialization code - DO NOT EDIT
gui_Singleton = 1;
gui_State = struct('gui_Name',       mfilename, ...
                   'gui_Singleton',  gui_Singleton, ...
                   'gui_OpeningFcn', @VVE_OpeningFcn, ...
                   'gui_OutputFcn',  @VVE_OutputFcn, ...
                   'gui_LayoutFcn',  [] , ...
                   'gui_Callback',   []);
if nargin & isstr(varargin{1})
    gui_State.gui_Callback = str2func(varargin{1});
end

if nargout
    [varargout{1:nargout}] = gui_mainfcn(gui_State, varargin{:});
else
    gui_mainfcn(gui_State, varargin{:});
end
% End initialization code - DO NOT EDIT


% --- Executes just before VVE is made visible.
function VVE_OpeningFcn(hObject, eventdata, handles, varargin)
% This function has no output args, see OutputFcn.
% hObject    handle to figure
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
% varargin   command line arguments to VVE (see VARARGIN)

% Choose default command line output for VVE
handles.output = hObject;

% Update handles structure
guidata(hObject, handles);

% UIWAIT makes VVE wait for user response (see UIRESUME)
% uiwait(handles.figure1);


% --- Outputs from this function are returned to the command line.
function varargout = VVE_OutputFcn(hObject, eventdata, handles)
% varargout  cell array for returning output args (see VARARGOUT);
% hObject    handle to figure
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Get default command line output from handles structure
varargout{1} = handles.output;


% --- Executes during object creation, after setting all properties.
function actual_CreateFcn(hObject, eventdata, handles)
% hObject    handle to actual (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc
    set(hObject,'BackgroundColor','white');
else
    set(hObject,'BackgroundColor',get(0,'defaultUicontrolBackgroundColor'));
end



function actual_Callback(hObject, eventdata, handles)
% hObject    handle to actual (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of actual as text
%        str2double(get(hObject,'String')) returns contents of actual as a double
global vact
vact=str2double(get(hObject, 'String'));


% --- Executes during object creation, after setting all properties.
function anterior_CreateFcn(hObject, eventdata, handles)
% hObject    handle to anterior (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc
    set(hObject,'BackgroundColor','white');
else
    set(hObject,'BackgroundColor',get(0,'defaultUicontrolBackgroundColor'));
end



function anterior_Callback(hObject, eventdata, handles)
% hObject    handle to anterior (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of anterior as text
%        str2double(get(hObject,'String')) returns contents of anterior as a double
global vant
vant=str2double(get(hObject, 'String'));
% --- Executes on button press in pushbutton1.
function pushbutton1_Callback(hObject, eventdata, handles)
% hObject    handle to pushbutton1 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
global vact vant 
Ea=abs((vact-vant)/vact)*100
set(handles.error, 'String', Ea);

% --- Executes on button press in pushbutton2.
function pushbutton2_Callback(hObject, eventdata, handles)
% hObject    handle to pushbutton2 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
set(handles.actual, 'String', ' ');
set(handles.error, 'String', ' ');
set(handles.anterior, 'String', ' ');

% --- Executes on button press in pushbutton3.
function pushbutton3_Callback(hObject, eventdata, handles)
% hObject    handle to pushbutton3 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

close
