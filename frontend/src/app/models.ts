export interface UserData {
    login: string;
    id: string;
    html_url: string;
    avatar_url: string;
}

export interface GoogleData {
    email: string;
    picture: string;
    name: string;
    sub: string;
}

export interface Contact {
	email: string;
	title: string;
	text: string;
    attachment: File;
}

export interface Club {
    name: string;
    id: string;
    heroImage: string;
}

export interface Stats {
    ownGoals: string;
    yellowCards: string;
    redCards: string;
    matches: string;
    goals: string;
    assists: string;
    // clean sheet = toNil
    // will need to hide this stats if player is not a goalkeeper
    cleanSheets: string;
    concededGoals : string;
    goalkeeper : boolean;
}

export interface PlayerSQL {
    id: string;
    name: string;
}

export interface Fixtures {
    fullMatchDate: string;
    matchTime: string;
    homeClubName: string;
    homeClubImage: string;
    awayClubName: string;
    awayClubImage: string;
    result: string;
}